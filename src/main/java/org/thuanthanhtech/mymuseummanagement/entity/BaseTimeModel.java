package org.thuanthanhtech.mymuseummanagement.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseTimeModel {

    @CreationTimestamp
    @Column(name = "create_Time")
    private Date creatDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "update_by")
    private String UpdateBy;

    @Column(name = "status")
    private Integer status;

}
