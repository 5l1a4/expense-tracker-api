package com.analistas.expensetrackerapi.domain.entity;

import com.analistas.expensetrackerapi.domain.enums.EnumCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumCategory category;

    private BigDecimal amount;

    private String description;

    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    private OffsetDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Expense(EnumCategory category, BigDecimal amount, String description, OffsetDateTime date, User user) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        Class<?> otherEffectiveClass = o instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        if (!thisEffectiveClass.equals(otherEffectiveClass)) return false;

        Expense expense = (Expense) o;
        return getId() != null && Objects.equals(getId(), expense.getId());
    }

    @Override
    public final int hashCode() {
        Class<?> effectiveClass = this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        return effectiveClass.hashCode();
    }
}
