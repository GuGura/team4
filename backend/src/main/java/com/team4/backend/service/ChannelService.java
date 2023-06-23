package com.team4.backend.service;

import com.team4.backend.mapper.ChannelMapper;
import com.team4.backend.model.ChannelMember;
import com.team4.backend.model.dto.MyChannelsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelMapper channelMapper;

    public List<MyChannelsDTO> getMyChannels(int memberUID) {
        List<MyChannelsDTO> list = new ArrayList<>();
        list.add(getChannelType(memberUID,"lobby", "/img/serverlist/discord_Icon.png"));
        list.addAll(channelMapper.findChannelsByMemberUID(memberUID));
        list.add(getChannelType(memberUID,"addServer", "/img/serverlist/add_server1.png"));
        list.add(getChannelType(memberUID,"public", "/img/serverlist/public_icon.png"));
        return list;
    }

    public MyChannelsDTO getChannelType(int memberUID, String channel_title,String channel_iconURL) {
        return MyChannelsDTO.builder()
                .channel_UID(0)
                .member_UID(memberUID)
                .channel_title(channel_title)
                .channel_icon_url(channel_iconURL)
                .build();
    }
}
