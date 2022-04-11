package com.choi.marvel.service;

import com.choi.marvel.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    Page<BoardPagingDTO> paging(Pageable pageable);

    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    BoardDetailDTO findById(Long boardId);

    Long update(BoardUpdateDTO boardUpdateDTO);

    void delete(Long boardId);

    List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO);

}
