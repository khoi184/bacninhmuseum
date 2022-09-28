package org.thuanthanhtech.mymuseummanagement.entity;

import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = ("id"))
//    private List<Media> mediaImage;
}
