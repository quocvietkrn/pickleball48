/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.Timestamp;

/**
 *
 * @author Minh Trung
 */
public class Order_Payments {
    private int payment_id;
    private int user_id;
    private int order_id;
    private double amount;
    private String payment_method;
    private String status;
    private Timestamp created_at;

     public Order_Payments() {
    }

    // Constructor có tham số
    public Order_Payments(int payment_id, int user_id, int order_id, double amount, String payment_method, String status, Timestamp created_at) {
        this.payment_id = payment_id;
        this.user_id = user_id;
        this.order_id = order_id;
        this.amount = amount;
        this.payment_method = payment_method;
        this.status = status;
        this.created_at = created_at;
    }

    public int getPayment_id() { return payment_id; }
    public void setPayment_id(int payment_id) { this.payment_id = payment_id; }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public int getOrder_id() { return order_id; }
    public void setOrder_id(int order_id) { this.order_id = order_id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }
}
