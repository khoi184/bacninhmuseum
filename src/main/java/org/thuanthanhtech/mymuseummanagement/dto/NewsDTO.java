package org.thuanthanhtech.mymuseummanagement.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private String search;
    private Integer type;
    private LocalDate date;
}
