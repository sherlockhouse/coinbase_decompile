package com.coinbase.v1.entity;

import com.coinbase.v1.deserializer.OrdersLifter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

public class OrdersResponse extends Response {
    private static final long serialVersionUID = 2721898279676340667L;
    private List<Order> _orders;

    public List<Order> getOrders() {
        return this._orders;
    }

    @JsonDeserialize(converter = OrdersLifter.class)
    public void setOrders(List<Order> orders) {
        this._orders = orders;
    }
}
