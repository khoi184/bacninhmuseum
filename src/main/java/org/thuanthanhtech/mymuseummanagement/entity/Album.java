package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="album")
public class Album extends BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tên albumm không được trống")
    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;
}
