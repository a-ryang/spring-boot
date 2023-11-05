package com.aryang.todo.global.pagination;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Page<T> {
    private final int totalItems;
    private final int itemsPerPage;
    private final int currentPage;
    private final int totalPages;
    private final List<T> items;


    @Builder
    public Page(int totalItems, int itemsPerPage, int currentPage, int totalPages, List<T> items) {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.items = items;
    }

}
