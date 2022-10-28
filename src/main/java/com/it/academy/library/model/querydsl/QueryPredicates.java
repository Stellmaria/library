package com.it.academy.library.model.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryPredicates {
    private final Collection<Predicate> predicates = new ArrayList<>();

    public static @NotNull QueryPredicates builder() {
        return new QueryPredicates();
    }

    public <T> QueryPredicates add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicate build() {
        return Optional.ofNullable(ExpressionUtils.allOf(predicates))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }

    @SuppressWarnings("unused")
    public Predicate buildOr() {
        return Optional.ofNullable(ExpressionUtils.anyOf(predicates))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }
}
