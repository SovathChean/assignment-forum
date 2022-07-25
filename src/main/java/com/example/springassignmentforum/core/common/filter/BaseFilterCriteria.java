package com.example.springassignmentforum.core.common.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilterCriteria {
    private int pageSize;
    private int pageNo;
    private Boolean paginated;
}
