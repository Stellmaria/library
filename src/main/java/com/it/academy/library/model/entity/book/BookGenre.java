package com.it.academy.library.model.entity.book;

import com.it.academy.library.model.entity.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "book_genre")
@SelectBeforeUpdate
public class BookGenre extends AbstractAuditingEntity<Integer> {
    private static final long serialVersionUID = 841705801046019455L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false,
            length = 64,
            unique = true
    )
    private String name;

    @ManyToMany(mappedBy = "genres")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BookGenre bookGenre = (BookGenre) o;

        return id != null && Objects.equals(id, bookGenre.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
