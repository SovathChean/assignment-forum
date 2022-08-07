package com.example.springassignmentforum.web.vo.request;

import com.example.springassignmentforum.core.model.UserModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Array;
import java.time.LocalDateTime;

@Data
public class PostCreationRequestVO {
    @NotBlank
    private String subject;
    private String description;
    @NotBlank
    @NotEmpty
    private Long userId;
}
