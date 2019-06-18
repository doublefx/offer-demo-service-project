package com.doublefx.demo.model;

import com.doublefx.demo.repository.OfferEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Offer {

	public static Offer fromEntity(OfferEntity entity) {
		return Offer.of(entity.getId(), entity.getDescription(), entity.getPrice(),
			entity.getExpiryDate(), entity.getDisabled());
	}

	public OfferEntity toEntity() {
		return OfferEntity.of(description, price, expiryDate);
	}

	@JsonProperty("id")
	@ApiModelProperty(example = "1")
	@EqualsAndHashCode.Include
	private Long id;

	@NonNull
	@JsonProperty("description")
	@ApiModelProperty(example = "This is the best offer")
	private String description;

	@NonNull
	@JsonProperty("price")
	@ApiModelProperty(example = "10.5")
	private BigDecimal price;

	@NonNull
	@JsonProperty("expiryDate")
	@ApiModelProperty(example = "2039-06-16T06:44:17Z")
	private Instant expiryDate;

	@JsonProperty("disabled")
	@ApiModelProperty(example = "false")
	private Boolean disabled;
}
