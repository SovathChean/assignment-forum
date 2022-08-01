package com.example.springassignmentforum.web.filter;

import com.example.springassignmentforum.core.common.filter.BaseFilterCriteria;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PostFilterCriteria extends BaseFilterCriteria {
    public static final String ORDER_BY_ID = "id";
    public String DEFAULT_ORDER_BY = ORDER_BY_ID;
    private String search;
    private Long creatorId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime fromDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime toDateTime;
}
