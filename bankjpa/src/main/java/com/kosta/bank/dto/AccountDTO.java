package com.kosta.bank.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String id;
    private String name;
    private Integer balance;
    private String type;
    private String grade;
}
