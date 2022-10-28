package com.it.academy.library.model.repository.filter.order.impl;

import com.it.academy.library.dto.filter.order.OrderTypeFilter;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.model.repository.filter.order.FilterOrderTypeRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.order.QOrderType.orderType;

@RequiredArgsConstructor
public class FilterOrderTypeRepositoryImpl implements FilterOrderTypeRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<OrderType> findAllByOrderTypeFilter(@NotNull OrderTypeFilter orderTypeFilter) {
        return new JPAQuery<OrderType>(entityManager)
                .select(orderType)
                .from(orderType)
                .where(OrderTypeFilter.queryPredicates(orderTypeFilter))
                .fetch();
    }
}
