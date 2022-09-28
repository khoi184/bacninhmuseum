package org.thuanthanhtech.mymuseummanagement.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="videos")
public class Videos extends  BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Media> mediaVideo;

}
