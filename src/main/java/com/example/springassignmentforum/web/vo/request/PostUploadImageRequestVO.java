package com.example.springassignmentforum.web.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadImageRequestVO {
    private Long[] photos;
}
