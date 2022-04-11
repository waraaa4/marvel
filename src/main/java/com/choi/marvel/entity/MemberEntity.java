package com.choi.marvel.entity;

import com.choi.marvel.dto.MemberSaveDTO;
import com.choi.marvel.dto.MemberUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberPhone;

    @Column
    private String memberFilename;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity toSaveEntity(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPhone(memberSaveDTO.getMemberPhone());
        memberEntity.setMemberFilename(memberSaveDTO.getMemberFilename());
        return memberEntity;
    }

    public static MemberEntity toUpdateEntity(MemberUpdateDTO memberUpdateDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberUpdateDTO.getMemberId());
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());
        memberEntity.setMemberPhone(memberUpdateDTO.getMemberPhone());
        memberEntity.setMemberFilename(memberUpdateDTO.getMemberFilename());
        return memberEntity;
    }
}
