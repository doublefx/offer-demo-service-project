package com.doublefx.demo.controller.v1;

import com.doublefx.demo.model.Offer;
import com.doublefx.demo.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer")
@AllArgsConstructor
public class OfferController implements OfferApi {

    private final OfferService offerService;

    @PutMapping(path = "/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Offer offer) {
        offerService.create(offer);
    }

    @GetMapping(path = "/getAll", produces = "application/json")
    public List<Offer> getAll() {
        return offerService.findAllEnabledNotExpired();
    }

    @GetMapping(path = "/disable", produces = MediaType.APPLICATION_JSON_VALUE)
    public void disable(Long offerId) {
        offerService.disable(offerId);
    }
}
