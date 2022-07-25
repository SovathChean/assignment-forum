package com.example.springassignmentforum.web.filter;

import com.example.springassignmentforum.core.common.filter.BaseFilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFilterCriteria extends BaseFilterCriteria {
    public static final String ORDER_BY_ID = "id";
    public String DEFAULT_ORDER_BY = ORDER_BY_ID;

    private String subject;
    private String description;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private LocalDate fromDate;
    private  LocalDate toDate;
}
