package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_order_items")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_items")
    private Long idOrderItem;

    @Column(name = "image_url")
    private String imageUrl;

    private Double discount;

    private Double price;

    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderModel order;

//    private ProductModel product;

    @Column(name = "id_product")
    private Long productId;

    public Long getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(Long idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
