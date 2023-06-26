package com.team4.backend.service;

import com.team4.backend.mapper.ContentMapper;
import com.team4.backend.model.dto.ContentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentMapper contentMapper;

    public List<ContentDTO> listContent(int pageNum, int memberUID){
        return contentMapper.listContent(pageNum, memberUID);
    }

    public void saveContent(String content) {
        System.out.println(content);
        contentMapper.saveContent(content);
    }

}
