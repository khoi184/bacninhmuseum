package org.thuanthanhtech.mymuseummanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsDTO {
    private String search;
    private Integer type;
    private LocalDate date;
}
