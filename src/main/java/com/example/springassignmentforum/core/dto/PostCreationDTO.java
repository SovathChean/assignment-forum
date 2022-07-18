package com.example.springassignmentforum.core.dto;


import com.example.springassignmentforum.core.model.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreationDTO {
    public String subject;
    public String description;
    public Long userId;
}
