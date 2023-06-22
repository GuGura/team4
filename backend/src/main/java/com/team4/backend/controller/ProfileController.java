package com.team4.backend.controller;

import com.team4.backend.model.dto.UserDetailDTO;
import com.team4.backend.service.ProfileService;
import com.team4.backend.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@RestController
public class ProfileController {

    @Autowired
    public ProfileController(ProfileService profileService){this.profileService = profileService;}

    private final ProfileService profileService;

   @PostMapping("api/v1/home/updateProfile")
    public void updateProfile(@RequestBody Map<String,String> params) throws IOException {
        //img 파일로 변환 및 저장
       String base64Data=params.get("imgPreview");
       String base64 = base64Data.substring(base64Data.lastIndexOf(",")+1);
       BufferedImage image = null;
       byte[] imageByte;

       Base64.Decoder decoder =  Base64.getDecoder();
       imageByte = decoder.decode(base64);
       ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
       image = ImageIO.read(bis);
       bis.close();

       //파일명
       String fileName = base64.substring(30,50)+".png";

       String uploadFolder = "C:\\upload\\images\\users\\"+UserUtil.getEmail().substring(0,UserUtil.getEmail().lastIndexOf("@"))+"\\icons\\";

       File outputfile = new File(uploadFolder+fileName);
       ImageIO.write(image, "png", outputfile);
       //저장완료

       UserDetailDTO userDetailDTO = new UserDetailDTO();
       userDetailDTO.setUserName(UserUtil.getEmail());
       userDetailDTO.setUser_description(params.get("user_description"));
       userDetailDTO.setNickName(params.get("nickName"));
       userDetailDTO.setIcon_URL(uploadFolder+fileName);

       profileService.updateProfile(userDetailDTO);
    }

    //@PostMapping("api/v1/home/updateProfile")
    public void createProfile(@RequestBody UserDetailDTO userDetail){
        String email = UserUtil.getEmail();
        userDetail.setUserName(email);
        profileService.createProfile(userDetail);
    }



}
