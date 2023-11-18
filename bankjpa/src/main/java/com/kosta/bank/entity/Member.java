package com.kosta.bank.entity;

import com.kosta.bank.dto.MemberDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 매칭할 테이블명과 클래스명이 다르게 할 경우 (name="")을 작성하여 맞춰주어야 한다
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Member {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String address;

    public static Member toEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .address(memberDTO.getAddress())
                .build();
    }
}