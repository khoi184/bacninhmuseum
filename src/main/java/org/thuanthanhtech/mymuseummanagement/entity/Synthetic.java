package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "synthetic")
public class Synthetic extends BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer type;

    private  String name;

    private  String title;

    private String content;

    private Long categoryDetailId;

}
