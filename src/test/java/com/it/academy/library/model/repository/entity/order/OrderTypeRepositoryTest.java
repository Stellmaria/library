package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.mapper.filter.order.OrderTypeFilterMapper;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Order type repository test.")
class OrderTypeRepositoryTest extends IntegrationTestBase {
    private static final Integer ORDER_TYPE_ID_3 = 3;
    private static final Integer ORDER_TYPE_ID_4 = 4;

    private final OrderTypeRepository orderTypeRepository;
    private final OrderTypeFilterMapper orderTypeFilterMapper;

    @Test
    @DisplayName("Save order type.")
    void saveOrderType() {
        var expectedCount = orderTypeRepository.count() + 1;
        var orderType = OrderType.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = orderTypeRepository.save(orderType);
        var actualCount = orderTypeRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Update order type.")
    void updateOrderType() {
        var orderType = orderTypeRepository.findById(ORDER_TYPE_ID_3);

        orderType.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            orderTypeRepository.save(it);
        });
        var actual = orderTypeRepository.findById(ORDER_TYPE_ID_3);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
            assertEquals(ORDER_TYPE_ID_3, it.getId());
        });
    }

    @Test
    @DisplayName("Delete order type.")
    void deleteOrderType() {
        var expected = orderTypeRepository.count() - 1;

        orderTypeRepository.deleteById(ORDER_TYPE_ID_4);
        var actual = orderTypeRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all order type by order type.")
    void findAllOrderTypeByOrderTypeFilter() {
        var orderType = OrderType.builder()
                .name(ConstantUtil.ORDER_TYPE_NAME_UNCONFIRMED)
                .build();

        var actual = orderTypeRepository.findAllByOrderTypeFilter(orderTypeFilterMapper.map(
                orderType));

        assertThat(actual).hasSize(1);
    }
}
