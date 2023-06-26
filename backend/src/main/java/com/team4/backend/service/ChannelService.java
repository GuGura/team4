package com.team4.backend.service;

import com.team4.backend.mapper.ChannelMapper;
import com.team4.backend.model.Channel;
import com.team4.backend.model.ChannelMember;
import com.team4.backend.model.dto.MyChannelsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelMapper channelMapper;

    public List<MyChannelsDTO> getMyChannels(int memberUID) {
        List<MyChannelsDTO> list = new ArrayList<>(channelMapper.findChannelsByMemberUID(memberUID));
        list.add(getChannelType(memberUID, "addServer", "/img/serverlist/add_server1.png", "addServer"));
        list.add(getChannelType(memberUID, "public", "/img/serverlist/public_icon.png", "public"));
        return list;
    }

    private MyChannelsDTO getChannelType(int memberUID, String channel_title, String channel_iconURL, String channel_type) {
        return MyChannelsDTO.builder()
                .channel_UID(0)
                .member_UID(memberUID)
                .channel_title(channel_title)
                .channel_icon_url(channel_iconURL)
                .channel_type(channel_type)
                .build();
    }

    public MyChannelsDTO createChannel(int memberUID, String fileURL, String serverName) {
        String URL = fileURL;
        if (URL.equals("/img/sidebar/choose.png")) {
            URL = null;
        }
        else {
            try {
                URL = imgURL(fileURL,memberUID);
                System.out.println(URL);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Channel channel = Channel.builder()
                .fileURL(URL)
                .nickName(memberUID)
                .serverName(serverName)
                .build();
        channelMapper.saveChannel(channel);
        int channel_UID = channelMapper.findChannelUIDByMemberUID(memberUID);
        System.out.println(channel_UID);
        channelMapper.saveChannelMember(channel_UID, memberUID, "ROLE_ADMIN");
        return channelMapper.findLastChannelByMemberUID(memberUID);
    }

    public String imgURL(String fileURL,int memberUID) throws IOException {
        String base64 = fileURL.substring(fileURL.lastIndexOf(",") + 1);
        BufferedImage image = null;
        byte[] imageByte;

        Base64.Decoder decoder = Base64.getDecoder();
        imageByte = decoder.decode(base64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        //파일명
        String fileName = base64.substring(30, 50) + ".png";

        String uploadFolder = "C:/upload/users/"+ memberUID+"/channels/";
        new File(uploadFolder).mkdirs();
        File outputfile = new File(uploadFolder + fileName);
        ImageIO.write(image, "png", outputfile);
        //저장완료
        return uploadFolder + fileName;
    }

}
