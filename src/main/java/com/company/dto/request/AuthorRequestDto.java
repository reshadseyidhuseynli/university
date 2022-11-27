package com.company.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorRequestDto {

    @NotBlank(message = "Author name can not be blank")
    private String name;

}
