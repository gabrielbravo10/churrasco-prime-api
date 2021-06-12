package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.Purchase;
import com.churrascoprime.api.dtos.PurchaseResponse;
import com.churrascoprime.api.services.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;
    private ModelMapper modelMapper;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, ModelMapper modelMapper) {
        this.checkoutService = checkoutService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }


}
