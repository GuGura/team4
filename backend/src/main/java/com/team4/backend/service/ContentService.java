package com.team4.backend.service;

import com.team4.backend.mapper.ContentMapper;
import com.team4.backend.model.dto.ContentDTO;
import com.team4.backend.util.CodeGenerator;
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

    public void saveContent(ContentDTO content) {
        contentMapper.saveContent(content);
    }

    public ContentDTO getContentById(int id){
        return contentMapper.getContentById(id);
    }

    public void deleteContent(int id, int writer_id){
        contentMapper.deleteContent(id, writer_id);
    }

}
