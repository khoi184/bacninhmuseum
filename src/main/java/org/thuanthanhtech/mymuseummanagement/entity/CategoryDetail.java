package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_detail")
public class CategoryDetail extends BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String title;

    private String content;

    private String description;

    private int categoryId;
}
