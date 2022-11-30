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
        var order = Order.builder()
                .user(getUser())
                .orderStatus(getOrderStatus())
                .orderDate(ORDER_DATE_11)
                .returnDate(ORDER_RETURN_DATE_21)
                .build();

        var actual = orderRepository.save(order);
        var actualCount = orderRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(order.getUser().getId(), actual.getUser().getId()),
                () -> assertEquals(order.getOrderStatus().getId(), actual.getOrderStatus().getId()),
                () -> assertEquals(order.getOrderDate(), actual.getOrderDate()),
                () -> assertEquals(order.getReturnDate(), actual.getReturnDate())
        );
    }

    @Test
    @DisplayName("Update order.")
    void updateOrder() {
        var order = orderRepository.findById(ORDER_ID_3);

        order.ifPresent(entity -> {
            entity.setUser(getUser());
            entity.setOrderStatus(getOrderStatus());
            entity.setOrderDate(ConstantUtil.ORDER_DATE_10);
            entity.setReturnDate(ORDER_RETURN_DATE_21);

            orderRepository.save(entity);
        });
        var actual = orderRepository.findById(ORDER_ID_3);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.USER_ID_5, entity.getUser().getId()),
                        () -> assertEquals(ORDER_STATUS_ID_1, entity.getOrderStatus().getId()),
                        () -> assertEquals(ConstantUtil.ORDER_DATE_10, entity.getOrderDate()),
                        () -> assertEquals(ORDER_RETURN_DATE_21, entity.getReturnDate()),
                        () -> assertEquals(ORDER_ID_3, entity.getId())
                )
        );
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
        var expected = 1;
        var user = new User();
        user.setFirstName(USER_FIRST_NAME_SERGEY);

        var actual = orderRepository.findAllByUserFilter(
                userFilterMapper.map(user)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all order by order filter.")
    void findAllOrderByOrderFilter() {
        var expected = 1;
        var order = new Order();
        order.setOrderDate(ORDER_DATE_25);

        var actual = orderRepository.findAllByOrderFilter(
                orderFilterMapper.map(order)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find orders by order status.")
    void findAllByStatus() {
        var expected = 1;
        var orderStatus = new OrderStatus();
        orderStatus.setName(ORDER_STATUS_NAME_APPROVED);

        var actual = orderRepository.findAllByOrderStatusFilter(
                orderStatusFilterMapper.map(orderStatus)
        );

        assertThat(actual).hasSize(expected);
    }

    private OrderStatus getOrderStatus() {
        return OrderStatus.builder()
                .id(OrderRepositoryTest.ORDER_STATUS_ID_1)
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(ConstantUtil.USER_ID_5)
                .build();
    }
}
