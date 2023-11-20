package com.kosta.board.dto;

import com.kosta.board.Entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	private String name;
	private String id;
	private String password;
	private String email;
	private String address;

	public Member toEntity() {
		return Member.builder()
				.id(id)
				.name(name)
				.password(password)
				.email(email)
				.address(address)
				.build();
	}
}
