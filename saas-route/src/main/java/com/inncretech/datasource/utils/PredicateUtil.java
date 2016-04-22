package com.inncretech.datasource.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import com.inncretech.data.domain.FilterField;
import com.inncretech.data.domain.RangeValue;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;

public class PredicateUtil {

    private PredicateUtil() {
    }

    public static <EntityType> BooleanExpression getPredicate(Class<EntityType> clazz, String entityName, List<FilterField> filterFields) {
        Assert.notNull(clazz);
        Assert.notNull(entityName);
        if (filterFields == null || filterFields.size() <= 0) {
            return null;
        }
        PathBuilder<EntityType> entityPath = new PathBuilder<EntityType>(clazz, entityName);
        BooleanExpression[] expressions = new BooleanExpression[filterFields.size()];
        int i = 0;
        for (FilterField filterField : filterFields) {
            BooleanExpression booleanExpression = getPredicate(entityPath, filterField);
            expressions[i++] = booleanExpression;
        }
        return BooleanExpression.allOf(expressions);
    }

    public static <EntityType> BooleanExpression getPredicate(PathBuilder<EntityType> entityPath, FilterField filterField) {
        if (filterField.getRange() != null) {
            return getRangeQuery(entityPath, filterField, Long.class);
        }

        try {
            Long parseNumber = NumberUtils.parseNumber(filterField.getValue(), Long.class);
            NumberPath<Long> path = entityPath.getNumber(filterField.getField(), Long.class);
            return path.eq(parseNumber);
        } catch (IllegalArgumentException e) {
        }
        StringPath path = entityPath.getString(filterField.getField());
        return path.containsIgnoreCase(filterField.getValue().toString());
    }

    private static <EntityType, RangeType> BooleanExpression getRangeQuery(PathBuilder<EntityType> entityPath, FilterField filterField,
            Class<RangeType> clazz) {
        if (filterField.getRange() == null) {
            return null;
        }

        if (clazz.isAssignableFrom(Long.class)) {
            return getRangeQueryForNumber(entityPath, filterField);
        } else if (clazz.isAssignableFrom(LocalDateTime.class)) {
            return getRangeQueryForDate(entityPath, filterField);
        }

        return null;
    }

    private static <EntityType> BooleanExpression getRangeQueryForNumber(PathBuilder<EntityType> entityPath, FilterField filterField) {

        Long rightNumber = null;
        Long leftNumber = null;
        RangeValue rangeValue = filterField.getRange();
        if (rangeValue.getEnd() != null && !rangeValue.getEnd().equals("")) {
            rightNumber = NumberUtils.parseNumber(rangeValue.getEnd(), Long.class);
        }
        if (rangeValue.getStart().trim() != null && !rangeValue.getStart().trim().equals("")) {
            leftNumber = NumberUtils.parseNumber(rangeValue.getStart(), Long.class);
        }
        NumberPath<Long> path = entityPath.getNumber(filterField.getField(), Long.class);
        if (rightNumber != null && leftNumber != null) {
            if (!rangeValue.isExclusiveEnd() && !rangeValue.isExclusiveStart()) {
                return path.between(leftNumber, rightNumber);
            } else if (rangeValue.isExclusiveEnd() && rangeValue.isExclusiveStart()) {
                return path.lt(rightNumber).and(path.gt(leftNumber));
            } else if (!rangeValue.isExclusiveEnd() && rangeValue.isExclusiveStart()) {
                return path.loe(rightNumber).and(path.gt(leftNumber));
            } else if (rangeValue.isExclusiveEnd() && !rangeValue.isExclusiveStart()) {
                return path.lt(rightNumber).and(path.goe(leftNumber));
            }
        }

        if (leftNumber != null) {
            if (!rangeValue.isExclusiveStart()) {
                return path.goe(leftNumber);
            } else {
                return path.gt(leftNumber);
            }
        }

        if (rightNumber != null) {
            if (!rangeValue.isExclusiveEnd()) {
                return path.loe(rightNumber);
            } else {
                return path.lt(rightNumber);
            }
        }

        return null;

    }

    private static <EntityType> BooleanExpression getRangeQueryForDate(PathBuilder<EntityType> entityPath, FilterField filterField) {

        LocalDateTime rightDateTime = null;
        LocalDateTime leftDateTime = null;
        RangeValue rangeValue = filterField.getRange();
        if (rangeValue.getEnd() != null && !rangeValue.getEnd().equals("")) {
            rightDateTime = LocalDateTime.parse(rangeValue.getEnd());
        }
        if (rangeValue.getStart().trim() != null && !rangeValue.getStart().trim().equals("")) {
            leftDateTime = LocalDateTime.parse(rangeValue.getStart());
        }
        DateTimePath<LocalDateTime> path = entityPath.getDateTime(filterField.getField(), LocalDateTime.class);
        if (rightDateTime != null && leftDateTime != null) {
            if (!rangeValue.isExclusiveEnd() && !rangeValue.isExclusiveStart()) {
                return path.between(leftDateTime, rightDateTime);
            } else if (rangeValue.isExclusiveEnd() && rangeValue.isExclusiveStart()) {
                return path.lt(rightDateTime).and(path.gt(leftDateTime));
            } else if (!rangeValue.isExclusiveEnd() && rangeValue.isExclusiveStart()) {
                return path.loe(rightDateTime).and(path.gt(leftDateTime));
            } else if (rangeValue.isExclusiveEnd() && !rangeValue.isExclusiveStart()) {
                return path.lt(rightDateTime).and(path.goe(leftDateTime));
            }
        }

        if (leftDateTime != null) {
            if (!rangeValue.isExclusiveStart()) {
                return path.goe(leftDateTime);
            } else {
                return path.gt(leftDateTime);
            }
        }

        if (rightDateTime != null) {
            if (!rangeValue.isExclusiveEnd()) {
                return path.loe(rightDateTime);
            } else {
                return path.lt(rightDateTime);
            }
        }
        return null;

    }

}
