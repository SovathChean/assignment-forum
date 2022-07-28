package com.example.springassignmentforum.core.common.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilterCriteria {
    private int pageSize;
    private int pageNo;
    private Boolean paginated;
    public int getPageSize() {
        return (this.pageSize != 0)? this.pageSize : 10;
    }
    public int getPageNo() {
        return (this.pageNo != 0)? this.pageNo : 1;
    }
}
