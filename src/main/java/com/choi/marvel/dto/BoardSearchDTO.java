package com.choi.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchDTO {

    private String choice;
    private String keyword;

}
