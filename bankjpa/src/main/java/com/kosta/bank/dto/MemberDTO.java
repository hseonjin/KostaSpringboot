package com.kosta.bank.dto;

import com.kosta.bank.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;

    public static MemberDTO toDTO(Member member) { // Entity를 DTO로 바꾸는 과정 (state가 중요하다)
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .password(member.getPassword())
                .email(member.getEmail())
                .address(member.getAddress()).build();
    }
}
