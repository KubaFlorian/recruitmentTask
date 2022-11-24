package com.recruitmenttask.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class SortService {

    public static Pageable resolve(int itemsPerPage, int pageNum, int sort) {
        if (itemsPerPage <= 0) itemsPerPage = 5;
        if (pageNum < 0) pageNum = 0;
        Pageable paging = PageRequest.of(pageNum, itemsPerPage, getSortType(sort));
        return paging;
    }

    public static Sort.Direction getSortType(int sort) {
        if (sort == 1) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

}
