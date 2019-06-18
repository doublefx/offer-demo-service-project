package com.doublefx.demo.service;

import com.doublefx.demo.model.Offer;
import com.doublefx.demo.repository.OfferRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public void create(Offer offer) {
        val entity = offer.toEntity();

        offerRepository.save(entity);
    }

    public List<Offer> findAllEnabledNotExpired() {
        val now = Instant.from(Instant.now());
        val allEnabledNotExpired = offerRepository.findByDisabledIsFalseAndExpiryDateIsAfter(now);

        return allEnabledNotExpired.stream().parallel()
          .map(Offer::fromEntity).collect(Collectors.toList());
    }

    public void disable(long offerId) {
        try {
            val offerEntity = offerRepository.findById(offerId);
            if (offerEntity.isPresent()) {
                val entity = offerEntity.get();
                entity.setDisabled(true);

                offerRepository.save(entity);
            }
        } catch (IllegalArgumentException ignored) {
        }
    }
}
