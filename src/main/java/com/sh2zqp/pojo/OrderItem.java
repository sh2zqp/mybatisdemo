package com.sh2zqp.pojo;

public class OrderItem {
    private int id;
    private int number;
    private Order order;
    private Product product;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem.xml{" +
                "id=" + id +
                ", number=" + number +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}