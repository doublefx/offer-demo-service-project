package com.doublefx.demo.controller.v1;

import com.doublefx.demo.model.Offer;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "offer")
public interface OfferApi {

    @ApiOperation(value = "Create one offer.", nickname = "create", tags = {"offer-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/offer/create",
        produces = { "application/json" },
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    @ApiModelProperty(example = "1")
    void create(@ApiParam(value = "offer", required = true) @Valid @RequestBody Offer offer);


    @ApiOperation(value = "Get all offers not expired and not disabled.", nickname = "getAll", response = Offer.class,
            responseContainer = "List", tags = {"offer-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Offer.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/offer/getAll",
            produces = {"application/json"},
            method = RequestMethod.GET)
    List<Offer> getAll();


    @ApiOperation(value = "Disable an offer.", nickname = "disable", tags = {"offer-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/offer/disable",
            produces = { "application/json" },
            method = RequestMethod.GET)
    void disable(@NotNull @ApiParam(value = "offerId", required = true)
                 @Valid @RequestParam(value = "offerId")
                         Long offerId);

}
