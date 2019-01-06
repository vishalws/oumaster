package com.gati.mdm.oumaster.common.data.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gati.mdm.common.util.CustomBeanUtils;
import com.gati.mdm.common.util.data.WhereClauseUtils;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.core.types.dsl.StringPath;

public class WhereClauseBuilder {

    private static final List<String> EQUALS_PREDICATE_COLUMNS = new ArrayList<>();

    private static final List<String> INTEGER_COLUMNS = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(WhereClauseBuilder.class);

    private BooleanBuilder conditions = null;

    PathBuilder<OuMaster> entityPath = null;

    static {
        EQUALS_PREDICATE_COLUMNS.add("type");
        EQUALS_PREDICATE_COLUMNS.add("status");
        EQUALS_PREDICATE_COLUMNS.add("gatiJubileeFlag");
        EQUALS_PREDICATE_COLUMNS.add("acClosedStatus");
        EQUALS_PREDICATE_COLUMNS.add("ouCategoryCode");
        EQUALS_PREDICATE_COLUMNS.add("operationalStatus");

        INTEGER_COLUMNS.add("companyId");
    }

    public void init() {
        conditions = new BooleanBuilder();
        entityPath = new PathBuilderFactory().create(OuMaster.class);
    }

    public BooleanBuilder build(OuMasterListRequest request) {
        LOGGER.debug("Entering build: {}", request);

        if (request != null) {
            init();
            Map<String, Object> requestBeanMap = CustomBeanUtils.getPropertyValueMap(request);
            LOGGER.debug("Request Bean Map built: {}", requestBeanMap);
            requestBeanMap.forEach(this::addConditions);
            LOGGER.debug("Conditions built: {}", conditions);
            return conditions;
        }
        return null;
    }

    private void addConditions(String column, Object valueObject) {
        if (Objects.isNull(valueObject)) {
            return;
        }

        String value = String.valueOf(valueObject);

        LOGGER.debug("addWhereConditions column:{}, value:{}", column, value);

        if (INTEGER_COLUMNS.contains(column) && StringUtils.isNumeric(value)) {
            // This is for CompanyId
            NumberPath<Integer> numberPath = entityPath.getNumber(column, Integer.class);
            WhereClauseUtils.entityAttributeEqualsInteger(numberPath, conditions, value);
        } else {
            StringPath path = entityPath.getString(column);
            if (EQUALS_PREDICATE_COLUMNS.contains(column)) {
                WhereClauseUtils.entityAttributeEquals(path, conditions, value);
            } else {
                WhereClauseUtils.entityAttributeContains(path, conditions, value);
            }
        }
    }
}
