package com.choi.marvel.service;

import com.choi.marvel.dto.MemberDetailDTO;
import com.choi.marvel.dto.MemberLoginDTO;
import com.choi.marvel.dto.MemberSaveDTO;
import com.choi.marvel.dto.MemberUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    MemberDetailDTO findByEmail(MemberLoginDTO memberLoginDTO);

    String emailCheck(String memberEmail);

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);

    List<MemberDetailDTO> findAll();

    Long update(MemberUpdateDTO memberUpdateDTO);

}
