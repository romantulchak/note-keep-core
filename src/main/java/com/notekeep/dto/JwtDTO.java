package com.notekeep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDTO {
    private String email;
    private String jwt;
}
