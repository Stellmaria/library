package com.it.academy.library.service.dto.filter.order;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.querydsl.QueryPredicates;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.filter.user.UserRoleFilter;
import com.it.academy.library.service.dto.filter.user.UserStatusFilter;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.it.academy.library.model.entity.order.QOrder.order;

/**
 * A DTO for the {@link Order} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFilter {
    private Long id;

    private UserFilter user;

    private OrderStatusFilter orderStatus;

    private LocalDateTime orderDate;

    private LocalDateTime returnDate;

    public static Predicate queryPredicates(@NotNull OrderFilter orderFilter) {
        return QueryPredicates.builder()
                .add(orderFilter.getId(), order.id::eq)
                .add(getUserId(orderFilter), order.user.id::eq)
                .add(getUsername(orderFilter), order.user.username::containsIgnoreCase)
                .add(getUSerFirstName(orderFilter), order.user.firstName::containsIgnoreCase)
                .add(getUserLastName(orderFilter), order.user.lastName::containsIgnoreCase)
                .add(getUserEmail(orderFilter), order.user.email::containsIgnoreCase)
                .add(getUserPassword(orderFilter), order.user.password::containsIgnoreCase)
                .add(getUserRoleId(orderFilter), order.user.userRole.id::eq)
                .add(getUserRoleName(orderFilter), order.user.userRole.name::containsIgnoreCase)
                .add(getUserStatusId(orderFilter), order.user.userStatus.id::eq)
                .add(getUserStatusName(orderFilter), order.user.userStatus.name::containsIgnoreCase)
                .add(getUserBirthday(orderFilter), order.user.birthday::eq)
                .add(getOrderStatusId(orderFilter), order.orderStatus.id::eq)
                .add(getOrderStatusName(orderFilter), order.orderStatus.name::containsIgnoreCase)
                .add(orderFilter.getOrderDate(), order.orderDate::eq)
                .add(orderFilter.getReturnDate(), order.returnDate::eq)
                .build();
    }

    private static String getUserStatusName(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getUserStatus)
                .map(UserStatusFilter::getName)
                .orElse(null);
    }

    private static String getUserRoleName(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getUserRole)
                .map(UserRoleFilter::getName)
                .orElse(null);
    }

    private static Integer getUserStatusId(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getUserStatus)
                .map(UserStatusFilter::getId)
                .orElse(null);
    }

    private static Integer getUserRoleId(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getUserRole)
                .map(UserRoleFilter::getId)
                .orElse(null);
    }

    private static LocalDate getUserBirthday(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getBirthday)
                .orElse(null);
    }

    private static String getUserPassword(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getPassword)
                .orElse(null);
    }

    private static String getUserEmail(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getEmail)
                .orElse(null);
    }

    private static String getUserLastName(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getLastName)
                .orElse(null);
    }

    private static String getUSerFirstName(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getFirstName)
                .orElse(null);
    }

    private static String getUsername(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getUsername)
                .orElse(null);
    }

    private static Long getUserId(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getUser())
                .map(UserFilter::getId)
                .orElse(null);
    }

    private static String getOrderStatusName(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getOrderStatus())
                .map(OrderStatusFilter::getName)
                .orElse(null);
    }

    private static Integer getOrderStatusId(@NotNull OrderFilter orderFilter) {
        return Optional.ofNullable(orderFilter.getOrderStatus())
                .map(OrderStatusFilter::getId)
                .orElse(null);
    }
}
