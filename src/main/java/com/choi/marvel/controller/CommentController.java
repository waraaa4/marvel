package com.choi.marvel.controller;

import com.choi.marvel.dto.CommentDetailDTO;
import com.choi.marvel.dto.CommentSaveDTO;
import com.choi.marvel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {

    private final CommentService cs;

    @PostMapping("save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO) {
        cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId());
        return commentList;
    }

}
