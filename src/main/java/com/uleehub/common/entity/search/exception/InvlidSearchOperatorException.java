/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.common.entity.search.exception;

import com.uleehub.common.entity.search.SearchOperator;

/**
 * <p>User: mtwu
 * <p>Date: 13-1-17 上午11:59
 * <p>Version: 1.0
 */
public final class InvlidSearchOperatorException extends SearchException {

    public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);
    }
}
