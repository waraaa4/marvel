package com.choi.marvel.service;

import com.choi.marvel.common.PagingConst;
import com.choi.marvel.dto.*;
import com.choi.marvel.entity.BoardEntity;
import com.choi.marvel.entity.MemberEntity;
import com.choi.marvel.repository.BoardRepository;
import com.choi.marvel.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getMemberEntity().getMemberEmail(),
                        board.getBoardTitle(),
                        board.getBoardHits())
        );
        return boardList;
    }

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis()+"-"+boardFilename;
        String savePath = "C:\\development\\source\\springboot\\marvelProject\\src\\main\\resources\\static\\image\\"+boardFilename;
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFilename(boardFilename);
        System.out.println("boardSaveDTO.getBoardFilename() = " + boardSaveDTO.getBoardFilename());

        MemberEntity memberEntity = mr.findById(boardSaveDTO.getMemberId()).get();
        BoardEntity boardEntity = BoardEntity.toSaveBoardEntity(boardSaveDTO, memberEntity);
        return br.save(boardEntity).getId();
    }

    @Override
    @Transactional
    public BoardDetailDTO findById(Long boardId) {
        int boardHits = br.boardHits(boardId);
        BoardEntity boardEntity = br.findById(boardId).get();
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        return boardDetailDTO;
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        MemberEntity memberEntity = mr.findById(boardUpdateDTO.getMemberId()).get();
        BoardEntity boardEntity = BoardEntity.toUpdateBoardEntity(boardUpdateDTO, memberEntity);
        return br.save(boardEntity).getId();
    }

    @Override
    public void delete(Long boardId) {
        br.deleteById(boardId);
    }

    @Override
    public List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO) {
        if (boardSearchDTO.getChoice().equals("writer")) {
            List<BoardEntity> boardEntityList = br.findByBoardWriterContaining(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailList(boardEntityList);
            System.out.println("boardDetailDTOList = " + boardDetailDTOList);
            return boardDetailDTOList;
        } else {
            List<BoardEntity> boardEntityList = br.findByBoardTitleContaining(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailList(boardEntityList);
            System.out.println("boardDetailDTOList = " + boardDetailDTOList);
            return boardDetailDTOList;
        }
    }


}