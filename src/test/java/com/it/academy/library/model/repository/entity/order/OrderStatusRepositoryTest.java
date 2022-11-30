package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.mapper.filter.order.OrderStatusFilterMapper;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Order status repository test.")
class OrderStatusRepositoryTest extends IntegrationTestBase {
    private static final String ORDER_STATUS_NAME_UNCONFIRMED = "Unconfirmed";
    private static final Integer ORDER_STATUS_ID_2 = 2;
    private static final Integer ORDER_STATUS_ID_3 = 3;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusFilterMapper orderStatusFilterMapper;

    private final OrderStatus orderStatus = new OrderStatus();

    @Test
    @DisplayName("Save order status.")
    void saveOrderStatus() {
        var expectedCount = orderStatusRepository.count() + 1;
        orderStatus.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = orderStatusRepository.save(orderStatus);
        var actualCount = orderStatusRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(orderStatus.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Update order status.")
    void update() {
        var orderStatus = orderStatusRepository.findById(ORDER_STATUS_ID_2);

        orderStatus.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            orderStatusRepository.save(entity);
        });
        var actual = orderStatusRepository.findById(ORDER_STATUS_ID_2);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName()),
                        () -> assertEquals(ORDER_STATUS_ID_2, entity.getId())
                )
        );
    }

    @Test
    @DisplayName("Delete order status.")
    void deleteOrderStatus() {
        var expected = orderStatusRepository.count() - 1;

        orderStatusRepository.deleteById(ORDER_STATUS_ID_3);
        var actual = orderStatusRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all order status by order status filter.")
    void findAllOrderStatusByOrderStatusFilter() {
        var expected = 1;
        orderStatus.setName(ORDER_STATUS_NAME_UNCONFIRMED);

        var actual = orderStatusRepository.findAllByOrderStatusFilter(
                orderStatusFilterMapper.map(orderStatus)
        );

        assertThat(actual).hasSize(expected);
    }
}
