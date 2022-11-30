package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.mapper.filter.order.OrderStatusFilterMapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Order repository test.")
class OrderRepositoryTest extends IntegrationTestBase {
    private final OrderRepository orderRepository;

    private final OrderFilterMapper orderFilterMapper;
    private final OrderStatusFilterMapper orderStatusFilterMapper;
    private final UserFilterMapper userFilterMapper;

    @Test
    @DisplayName("Save order.")
    void saveOrder() {
        var expectedCount = orderRepository.count() + 1;
        var order = Order.builder()
                .user(ConstantUtil.getUser())
                .orderStatus(ConstantUtil.getOrderStatus())
                .orderDate(ConstantUtil.ORDER_DATE_2022_10_11_T_14_35)
                .returnDate(ConstantUtil.ORDER_RETURN_DATE_2022_10_21_T_18_05)
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
        var order = orderRepository.findById(ConstantUtil.ORDER_ID_3);

        order.ifPresent(entity -> {
            entity.setUser(ConstantUtil.getUser());
            entity.setOrderStatus(ConstantUtil.getOrderStatus());
            entity.setOrderDate(ConstantUtil.ORDER_DATE_2022_10_23_T_14_35);
            entity.setReturnDate(ConstantUtil.ORDER_RETURN_DATE_2022_10_21_T_18_05);

            orderRepository.save(entity);
        });
        var actual = orderRepository.findById(ConstantUtil.ORDER_ID_3);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.USER_ID_5, entity.getUser().getId()),
                        () -> assertEquals(ConstantUtil.ORDER_STATUS_ID_1, entity.getOrderStatus().getId()),
                        () -> assertEquals(ConstantUtil.ORDER_DATE_2022_10_23_T_14_35, entity.getOrderDate()),
                        () -> assertEquals(ConstantUtil.ORDER_RETURN_DATE_2022_10_21_T_18_05, entity.getReturnDate()),
                        () -> assertEquals(ConstantUtil.ORDER_ID_3, entity.getId())
                )
        );
    }

    @Test
    @DisplayName("Deleted order.")
    void deleteOrder() {
        var expected = orderRepository.count() - 1;

        orderRepository.deleteById(ConstantUtil.ORDER_ID_2);
        var actual = orderRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all order by user filter.")
    void findAllOrderByUserFilter() {
        var expected = 1;
        var user = new User();
        user.setFirstName(ConstantUtil.USER_FIRST_NAME_SERGEY);

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
        order.setOrderDate(ConstantUtil.ORDER_DATE_2022_10_25_T_18_00);

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
        orderStatus.setName(ConstantUtil.ORDER_STATUS_NAME_APPROVED);

        var actual = orderRepository.findAllByOrderStatusFilter(
                orderStatusFilterMapper.map(orderStatus)
        );

        assertThat(actual).hasSize(expected);
    }
}
