package com.example.springassignmentforum.web.vo.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class UserResponseVO {
    private Long id;
    private String name;
    private String phone;
}
