package com.choi.marvel.service;

import com.choi.marvel.dto.CommentDetailDTO;
import com.choi.marvel.dto.CommentSaveDTO;
import com.choi.marvel.entity.BoardEntity;
import com.choi.marvel.entity.CommentEntity;
import com.choi.marvel.entity.MemberEntity;
import com.choi.marvel.repository.BoardRepository;
import com.choi.marvel.repository.CommentRepository;
import com.choi.marvel.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository cr;
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        MemberEntity memberEntity = mr.findById(commentSaveDTO.getMemberId()).get();
        CommentEntity commentEntity = CommentEntity.toSaveCommentEntity(commentSaveDTO, boardEntity, memberEntity);
        return cr.save(commentEntity).getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentDetailDTO> commentList = CommentEntity.toCommentEntityList(commentEntityList);
        for (CommentEntity c: commentEntityList) {
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }
}
