package com.kosta.board.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    @Column
    private String directory;
    @Column
    private String name;
    @Column
    private Long size;
    @Column
    private String contenttype;
    @Column
    private Date uploaddate;
    @Column
    private byte[] data;
}
