package com.it.academy.library.model.entity.user;

import com.it.academy.library.model.entity.AbstractAuditingEntity;
import com.it.academy.library.model.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@SelectBeforeUpdate
public class User extends AbstractAuditingEntity<Long> {
    private static final long serialVersionUID = 5877272640744592887L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "login",
            nullable = false,
            length = 50,
            unique = true
    )
    @Size(min = 3, max = 50)
    @NotBlank
    private String username;

    @Column(name = "first_name",
            nullable = false,
            length = 99
    )
    @Size(min = 3, max = 99)
    @NotBlank
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            length = 99
    )
    @Size(min = 3, max = 99)
    @NotBlank
    private String lastName;

    @Column(nullable = false,
            length = 50,
            unique = true
    )
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_role_id",
            nullable = false
    )
    @ToString.Exclude
    private UserRole userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_status_id",
            nullable = false
    )
    @ToString.Exclude
    private UserStatus userStatus;

    @Column(nullable = false)
    private LocalDate birthday;

    private String image;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
