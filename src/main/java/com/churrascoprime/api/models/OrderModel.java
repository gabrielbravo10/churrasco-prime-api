package com.churrascoprime.api.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class OrderModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_payment")
    private String orderPayment;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerModel customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private AddressModel address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItemModel> orderItems = new HashSet<>();

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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public Set<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    public void add(OrderItemModel item) {
        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }

    public void update(OrderModel updatedOrderModel) {
        this.orderTrackingNumber = updatedOrderModel.getOrderTrackingNumber();
        this.orderItems = updatedOrderModel.getOrderItems();
        this.address = updatedOrderModel.getAddress();
        this.customer = updatedOrderModel.getCustomer();
        this.rating = updatedOrderModel.getRating();
        this.orderPayment = updatedOrderModel.getOrderPayment();
        this.totalPrice = updatedOrderModel.getTotalPrice();
        this.totalQuantity = updatedOrderModel.getTotalQuantity();
        this.orderStatus = updatedOrderModel.getOrderStatus();
    }

}
