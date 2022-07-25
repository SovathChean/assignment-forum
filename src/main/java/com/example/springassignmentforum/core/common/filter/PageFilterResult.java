package com.example.springassignmentforum.core.common.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageFilterResult<T>{
    private Long totalRows;
    private List<T> pageData;
}
