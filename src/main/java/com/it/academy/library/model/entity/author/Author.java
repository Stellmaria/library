package com.it.academy.library.model.entity.author;

import com.it.academy.library.model.entity.AbstractAuditingEntity;
import com.it.academy.library.model.entity.BooksAuthors;
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
@SelectBeforeUpdate
public class Author extends AbstractAuditingEntity<Long> {
    private static final long serialVersionUID = 4134269130239323975L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "first_name",
            nullable = false,
            length = 64
    )
    @Size(min = 3, max = 64)
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            length = 64
    )
    @Size(min = 3, max = 64)
    private String lastName;

    @Column(name = "image_path")
    @NotBlank
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_role_id")
    @ToString.Exclude
    private AuthorRole authorRole;

    private LocalDate birthday;

    @Column(name = "date_death")
    private LocalDate dateDeath;

    private String description;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<BooksAuthors> booksAuthors;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Author author = (Author) o;

        return id != null && Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
