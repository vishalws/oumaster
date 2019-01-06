package com.gati.mdm.oumaster.common.data.specification;

import org.apache.commons.lang3.StringUtils;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {

    private static final String WILDCARD = "%";

    public abstract Specification<T> getFilter(U request);

    protected String containsLowerCase(String searchField) {
        return WILDCARD + searchField.toLowerCase() + WILDCARD;
    }

    protected String equalsLowerCase(String searchField) {
        return searchField.toLowerCase();
    }

    protected Specification<T> entityAttributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if (StringUtils.isBlank(value)) {
                return null;
            }

            return cb.like(cb.lower(root.get(attribute)), containsLowerCase(value));
        };
    }

    protected Specification<T> entityAttributeEquals(String attribute, String value) {
        return (root, query, cb) -> {
            if (StringUtils.isBlank(value)) {
                return null;
            }

            return cb.equal(cb.lower(root.get(attribute)), equalsLowerCase(value));
        };
    }
}
