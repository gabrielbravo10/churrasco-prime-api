package com.churrascoprime.api.services;

import com.churrascoprime.api.dtos.Purchase;
import com.churrascoprime.api.dtos.PurchaseResponse;
import com.churrascoprime.api.dtos.address.AddressFormDto;
import com.churrascoprime.api.models.AddressModel;
import com.churrascoprime.api.models.CustomerModel;
import com.churrascoprime.api.models.OrderItemModel;
import com.churrascoprime.api.models.OrderModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerService customerService;
    private ModelMapper modelMapper;
    private AddressService addressService;
    private CityService cityService;

    @Autowired
    public CheckoutServiceImpl(CustomerService customerService, ModelMapper modelMapper,
                               AddressService addressService, CityService cityService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
        this.cityService = cityService;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        CustomerModel customer = modelMapper.map(purchase.getCustomer(), CustomerModel.class);
        customerService.save(customer);

        AddressModel address = modelMapper.map(purchase.getAddress(), AddressModel.class);
        addCity(address, purchase.getAddress());
        address.setCustomer(customer);
        addressService.save(address);

        OrderModel order = purchase.getOrder();
        order.setOrderTrackingNumber(generateOrderTrackingNumber());

        Set<OrderItemModel> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        order.setAddress(address);
        order.setCustomer(customer);
        customer.add(order);
        address.setCustomer(customer);
        addressService.save(address);

        return new PurchaseResponse(order.getOrderTrackingNumber());
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    private void addCity(AddressModel address, AddressFormDto addressFormDto) {
        address.setCity(cityService.findById(addressFormDto.getCity()));
    }
}
