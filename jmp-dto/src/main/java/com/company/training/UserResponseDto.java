package com.company.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
