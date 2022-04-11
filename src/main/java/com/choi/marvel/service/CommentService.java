package com.choi.marvel.service;

import com.choi.marvel.dto.CommentDetailDTO;
import com.choi.marvel.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);

}
