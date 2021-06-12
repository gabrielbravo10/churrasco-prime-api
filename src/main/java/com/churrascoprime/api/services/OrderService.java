package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.OrderModel;
import com.churrascoprime.api.models.ProductModel;
import com.churrascoprime.api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String NOT_FOUND = "order.notFound";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel findById(Long idOrder) {
        return orderRepository.findById(idOrder).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<OrderModel> findAll(Pageable pageable) {
        return orderRepository.findAllByDateDeletedIsNull(pageable);
    }

    public OrderModel save(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public OrderModel update(OrderModel updatedOrderModel) {
        OrderModel orderModel = findById(updatedOrderModel.getIdOrder());
        orderModel.update(updatedOrderModel);
        return orderModel;
    }

    public void delete(Long idOrder) {
        OrderModel orderModel = findById(idOrder);
        orderModel.setDateDeleted(new Date());
        orderRepository.save(orderModel);
    }

    public Page<OrderModel> findAllByCustomerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByCustomerEmailAndDateDeletedIsNullOrderByDateCreated(email, pageable);
    }
}
