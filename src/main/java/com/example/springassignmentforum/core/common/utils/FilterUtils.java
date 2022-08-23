package com.example.springassignmentforum.core.common.utils;

import com.example.springassignmentforum.core.common.filter.BaseFilterCriteria;
import com.example.springassignmentforum.core.common.filter.FilterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class FilterUtils {
    public static <T extends BaseFilterCriteria> T createFilterCriteria(FilterRequest filterRequest, Class<T> searchClass)
    {
        String filterCriteriaString = StringUtils.isNotBlank(filterRequest.getFilterCriteriaString())
                ? filterRequest.getFilterCriteriaString()
                : "{}";

        T filterCriteria = JsonUtils.parse(filterCriteriaString, searchClass);
        filterCriteria.setPageNo(filterRequest.getPageNo());
        filterCriteria.setPageSize(filterRequest.getPageSize());
        filterCriteria.setPaginated(filterRequest.getPaginated());
        return filterCriteria;
    }

}
