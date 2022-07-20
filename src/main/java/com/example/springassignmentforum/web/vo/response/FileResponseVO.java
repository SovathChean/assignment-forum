package com.example.springassignmentforum.web.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseVO {
    private Long id;
    private String fileName;
    private Boolean isUsed;
    private LocalDateTime createdAt;
}
