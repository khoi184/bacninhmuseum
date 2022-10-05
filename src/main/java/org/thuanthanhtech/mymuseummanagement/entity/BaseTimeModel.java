package org.thuanthanhtech.mymuseummanagement.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseTimeModel {

    @CreationTimestamp
    @Column(name = "create_Time")
    private LocalDate creatDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "update_by")
    private String UpdateBy;

    @Column(name = "status")
    private Integer status;

}
