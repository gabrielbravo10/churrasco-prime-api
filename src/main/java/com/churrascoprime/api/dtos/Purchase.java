package com.churrascoprime.api.dtos;

import com.churrascoprime.api.dtos.address.AddressFormDto;
import com.churrascoprime.api.dtos.customer.CustomerFormDto;
import com.churrascoprime.api.models.OrderItemModel;
import com.churrascoprime.api.models.OrderModel;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private CustomerFormDto customer;
    private AddressFormDto address;
    private OrderModel order;
    private Set<OrderItemModel> orderItems;

    public OrderModel getOrder() {
        return order;
    }

    public AddressFormDto getAddress() {
        return address;
    }

    public CustomerFormDto getCustomer() {
        return customer;
    }

    public Set<OrderItemModel> getOrderItems() {
        return orderItems;
    }

}
