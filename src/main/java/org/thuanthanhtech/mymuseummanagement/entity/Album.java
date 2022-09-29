package org.thuanthanhtech.mymuseummanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = ("album"), cascade = CascadeType.ALL)
    private List<Media> mediaImage;
}
