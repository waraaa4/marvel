package com.choi.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {

    private String BoardTitle;
    private String BoardContents;
    private Long memberId;
    private MultipartFile boardFile;
    private String boardFilename;

}
