package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News extends BaseTimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer type;

    private String name;

    private String title;

    private String slug;

    private String content;

    private Long categoryDetailId;

    private  String author;

    private Integer publish;


}
