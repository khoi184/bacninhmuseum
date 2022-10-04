package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category extends  BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tên danh mục không được trống")
    @Column(name = "name")
    private String name;


}
