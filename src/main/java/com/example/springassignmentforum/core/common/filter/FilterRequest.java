package com.example.springassignmentforum.core.common.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterRequest {
    private int pageNo;
    private int pageSize;
    private Boolean paginated;
    private String filterCriteriaString;
}
