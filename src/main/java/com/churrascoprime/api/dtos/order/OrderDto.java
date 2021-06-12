package com.churrascoprime.api.dtos.order;

import com.churrascoprime.api.dtos.address.AddressDto;
import com.churrascoprime.api.dtos.customer.CustomerDto;
import com.churrascoprime.api.models.AddressModel;
import com.churrascoprime.api.models.CustomerModel;
import com.churrascoprime.api.models.OrderItemModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {


    private Long idOrder;
    private String orderTrackingNumber;
    private Double totalPrice;
    private Long totalQuantity;
    private String orderStatus;
    private String orderPayment;
    private Double rating;
    private CustomerDto customer;
    private AddressDto address;
    private Date dateCreated;
//    private Set<OrderItemModel> orderItems = new HashSet<>();

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(String orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

//    public Set<OrderItemModel> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(Set<OrderItemModel> orderItems) {
//        this.orderItems = orderItems;
//    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
