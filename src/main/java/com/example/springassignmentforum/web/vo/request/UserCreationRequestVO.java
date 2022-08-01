package com.example.springassignmentforum.web.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserCreationRequestVO {
    @NotBlank
    @Size(min = 9, max = 10)
    @Pattern(regexp = "^[0-9]*$", message = "Must be a valid number")
    private String phone;

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String password;
}
