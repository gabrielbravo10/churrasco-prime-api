package com.churrascoprime.api.services;

import com.churrascoprime.api.dtos.Purchase;
import com.churrascoprime.api.dtos.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
