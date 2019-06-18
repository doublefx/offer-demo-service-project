package com.doublefx.demo.repository;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "offer")
public class OfferEntity {

    public static OfferEntity of(String description, BigDecimal price, Instant expiryDate) {
        return new OfferEntity(0L, description, price, expiryDate, false);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id = 0L;

    @Column
    private String description = "None";

    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(nullable = false)
    private Instant expiryDate = Instant.now();

    @Column(nullable = false)
    @Setter
    private Boolean disabled = false;
}
