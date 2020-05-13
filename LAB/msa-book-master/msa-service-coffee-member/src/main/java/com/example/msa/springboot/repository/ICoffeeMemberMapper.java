package com.example.msa.springboot.repository;

import com.example.msa.springboot.repository.dvo.MemberDVO;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ICoffeeMemberMapper {
    MemberDVO existsByMemberName(MemberDVO memberDVO);

    int createMemberTable();

    int insertMemberData();
}
