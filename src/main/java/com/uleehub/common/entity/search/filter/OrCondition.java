/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.common.entity.search.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * or 条件
 * <p>User: mtwu
 * <p>Date: 13-5-24 下午2:51
 * <p>Version: 1.0
 */
public class OrCondition implements SearchFilter {

    private List<SearchFilter> orFilters = Lists.newArrayList();

    OrCondition() {
    }

    public OrCondition add(SearchFilter filter) {
        this.orFilters.add(filter);
        return this;
    }

    public List<SearchFilter> getOrFilters() {
        return orFilters;
    }

    @Override
    public String toString() {
        return "OrCondition{" + orFilters + '}';
    }
}
