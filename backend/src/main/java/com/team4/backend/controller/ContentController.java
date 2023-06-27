package com.team4.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team4.backend.model.dto.ContentDTO;
import com.team4.backend.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
public class ContentController {

    @Autowired
    public ContentController(ContentService contentService){this.contentService = contentService; }

    private final ContentService contentService;

    //글 불러오기(페이징)
    @ResponseBody
    @PostMapping("/content/listByPage")
    public List<ContentDTO> listContent(int last, HttpServletRequest request){
        List<ContentDTO> contents = new ArrayList<>();
        return contents;

    }

    //글 저장
    @ResponseBody
    @PostMapping("api/v1/home/saveContent")
    public void saveQuill(@RequestBody String json) throws Exception{
        contentService.saveContent(json);
    }

    //이미지 저장
    @ResponseBody
    @PostMapping("api/vi/home/imageWrite")
    public String uploadTestPOST(MultipartFile[] uploadFile) {
        String uploadFolder = "C:\\upload\\images";
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

    //이미지 글쓰기창에 불러오기
    @ResponseBody
    @GetMapping(value = "api/v1/home/display")
    public ResponseEntity<byte[]> showImageGET(
            @RequestParam("fileName") String fileName
    ) {
        File file = new File("C:\\upload\\images\\" + fileName);

        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders header = new HttpHeaders();

        /*
        Files.probeContentType() 해당 파일의 Content 타입을 인식(image, text/plain ...)
        없으면 null 반환

        file.toPath() -> file 객체를 Path객체로 변환

        */
            header.add("Content-type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
