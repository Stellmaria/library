package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.mapper.filter.order.OrderStatusFilterMapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Order repository test.")
class OrderRepositoryTest extends IntegrationTestBase {
    private static final String USER_FIRST_NAME_SERGEY = "Sergey";
    private static final String ORDER_STATUS_NAME_APPROVED = "Approved";
    private static final Integer ORDER_STATUS_ID_1 = 1;
    private static final LocalDateTime ORDER_DATE_25 =
            LocalDateTime.of(2022, 10, 25, 18, 0);
    private static final LocalDateTime ORDER_DATE_11 =
            LocalDateTime.of(2022, 10, 11, 14, 35);
    private static final LocalDateTime ORDER_RETURN_DATE_21 =
            LocalDateTime.of(2022, 10, 21, 18, 5);
    private static final Long ORDER_ID_3 = 3L;
    private static final Long ORDER_ID_2 = 2L;

    private final OrderRepository orderRepository;
    private final OrderFilterMapper orderFilterMapper;
    private final OrderStatusFilterMapper orderStatusFilterMapper;
    private final UserFilterMapper userFilterMapper;

    @Test
    @DisplayName("Save order.")
    void saveOrder() {
        var expectedCount = orderRepository.count() + 1;
        var user = User.builder()
                .id(ConstantUtil.USER_ID_5)
                .build();
        var orderStatus = OrderStatus.builder()
                .id(ORDER_STATUS_ID_1)
                .build();
        var order = Order.builder()
                .user(user)
                .orderStatus(orderStatus)
                .orderDate(ORDER_DATE_11)
                .returnDate(ORDER_RETURN_DATE_21)
                .build();

        var actual = orderRepository.save(order);
        var actualCount = orderRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.USER_ID_5, actual.getUser().getId()),
                () -> assertEquals(ORDER_STATUS_ID_1, actual.getOrderStatus().getId()),
                () -> assertEquals(ORDER_DATE_11, actual.getOrderDate()),
                () -> assertEquals(ORDER_RETURN_DATE_21, actual.getReturnDate())
        );
    }

    @Test
    @DisplayName("Update order.")
    void updateOrder() {
        var user = User.builder()
                .id(ConstantUtil.USER_ID_5)
                .build();
        var orderStatus = OrderStatus.builder()
                .id(ORDER_STATUS_ID_1)
                .build();
        var order = orderRepository.findById(ORDER_ID_3);

        order.ifPresent(it -> {
            it.setUser(user);
            it.setOrderStatus(orderStatus);
            it.setOrderDate(ConstantUtil.ORDER_DATE_10);
            it.setReturnDate(ORDER_RETURN_DATE_21);
            orderRepository.save(it);
        });
        var actual = orderRepository.findById(ORDER_ID_3);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.USER_ID_5, it.getUser().getId());
            assertEquals(ORDER_STATUS_ID_1, it.getOrderStatus().getId());
            assertEquals(ConstantUtil.ORDER_DATE_10, it.getOrderDate());
            assertEquals(ORDER_RETURN_DATE_21, it.getReturnDate());
            assertEquals(ORDER_ID_3, it.getId());
        });
    }

    @Test
    @DisplayName("Deleted order.")
    void deleteOrder() {
        var expected = orderRepository.count() - 1;

        orderRepository.deleteById(ORDER_ID_2);
        var actual = orderRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all order by user filter.")
    void findAllOrderByUserFilter() {
        var user = User.builder()
                .firstName(USER_FIRST_NAME_SERGEY)
                .build();

        var actual = orderRepository.findAllByUserFilter(userFilterMapper.map(user));

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all order by order filter.")
    void findAllOrderByOrderFilter() {
        var order = Order.builder()
                .orderDate(ORDER_DATE_25)
                .build();

        var actual = orderRepository.findAllByOrderFilter(orderFilterMapper.map(order));

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find orders by order status.")
    void findAllByStatus() {
        var orderStatus = OrderStatus.builder()
                .name(ORDER_STATUS_NAME_APPROVED)
                .build();

        var actual = orderRepository.findAllByOrderStatusFilter(orderStatusFilterMapper.map(
                orderStatus));

        assertThat(actual).hasSize(1);
    }
}
