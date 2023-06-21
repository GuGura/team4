package com.team4.backend.controller;

import com.team4.backend.model.dto.EventDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class ProfileController {

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
}
