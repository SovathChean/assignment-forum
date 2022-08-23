package com.example.springassignmentforum.core.common.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FilterUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static <T> T parse (String jsonSearchCriteria, Class<T> searchClass){
        T searchCriteria = null;
        try
        {
            searchCriteria = MAPPER.readValue(jsonSearchCriteria, searchClass);
        }catch (IOException e)
        {
            LOG.info("Something went wrong while parsing stringified JSON", e);
        }

        return searchCriteria;
    }

}
