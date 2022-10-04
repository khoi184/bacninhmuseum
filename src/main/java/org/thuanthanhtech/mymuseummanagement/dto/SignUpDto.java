package org.thuanthanhtech.mymuseummanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String name;
    private String role;
}
