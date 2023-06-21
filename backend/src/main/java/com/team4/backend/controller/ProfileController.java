package com.team4.backend.controller;

import com.team4.backend.model.dto.UserDetailDTO;
import com.team4.backend.service.ProfileService;
import com.team4.backend.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RestController
public class ProfileController {

    @Autowired
    public ProfileController(ProfileService profileService){this.profileService = profileService;}

    private final ProfileService profileService;

    @PostMapping("api/v1/home/changeProfileImage")
    public String saveProfileImage(MultipartFile[] uploadFile){
        String uploadFolder = "C:\\upload\\images\\profile";
        File uploadPath = new File(uploadFolder);
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        String uploadFileName = null;
        for (MultipartFile multipartFile : uploadFile) {

            uploadFileName = multipartFile.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            uploadFileName = uuid + "_" + uploadFileName;

            File saveFile = new File(uploadPath, uploadFileName);

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return uploadFileName;
    }

   @PutMapping("api/v1/home/updateProfile")
    public void updateProfile(@RequestBody UserDetailDTO userDetail){
        String email = UserUtil.getEmail();
        userDetail.setUserName(email);
        profileService.updateProfile(userDetail);
    }

    //@PostMapping("api/v1/home/updateProfile")
    public void createProfile(@RequestBody UserDetailDTO userDetail){
        String email = UserUtil.getEmail();
        userDetail.setUserName(email);
        profileService.createProfile(userDetail);
    }

    @PostMapping("api/v1/home/updateIcon")
    public void updateIcon(@RequestBody File uploadFile){
        String uploadFolder = "C:\\upload\\images\\users\\"+UserUtil.getEmail().substring(0,UserUtil.getEmail().lastIndexOf("@"))+"\\icons";
        File file = new File(uploadFolder);
        if (file.exists() == false) {
            file.mkdirs();
        }String uploadFileName = null;
        File uploadingFile = new File(file, uploadFile);
    }
}
