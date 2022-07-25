package com.example.springassignmentforum.core.common.utils;

import com.example.springassignmentforum.core.common.filter.BaseFilterCriteria;
import com.example.springassignmentforum.core.common.filter.FilterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FilterUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FilterUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T extends BaseFilterCriteria> T createFilterCriteria(FilterRequest filterRequest, Class<T> searchClass)
    {
        String filterCriteriaString = StringUtils.isNotBlank(filterRequest.getFilterCriteriaString())
                ? filterRequest.getFilterCriteriaString()
                : "{}";
        System.out.println(filterCriteriaString);
        T filterCriteria = JsonUtils.parse(filterCriteriaString, searchClass);
        filterCriteria.setPageNo(filterRequest.getPageNo());
        filterCriteria.setPageSize(filterRequest.getPageSize());
        filterCriteria.setPaginated(filterRequest.getPaginated());
        return filterCriteria;
    }

}
