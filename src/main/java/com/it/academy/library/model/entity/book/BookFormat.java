package com.it.academy.library.model.entity.book;

import com.it.academy.library.model.entity.BaseEntity;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "book_format")
@SelectBeforeUpdate
public class BookFormat implements BaseEntity<Integer> {
    private static final long serialVersionUID = 6871968670353574959L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false,
            length = 64,
            unique = true
    )
    @Size(min = 3, max = 64)
    @NotBlank
    private String name;

    @OneToMany(
            mappedBy = "bookFormat",
            cascade = CascadeType.ALL
    )
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
        BookFormat that = (BookFormat) o;

        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
