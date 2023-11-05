package com.aryang.todo.global.pagination;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import jakarta.validation.constraints.Min;

@Getter
public class PageRequest {

    @Min(value = 1, message = "Page number must be 1 or greater")
    @Max(value = 1000, message = "Page number must be 1000 or less")
    private int page;

    @Min(value = 1, message = "Page size must be 1 or greater")
    @Max(value = 1000, message = "Page size must be 1000 or less")
    private int size;

    public PageRequest(Integer page, Integer size) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;
    }

    public int getOffset() {
        return (page - 1) * size;
    }

}

