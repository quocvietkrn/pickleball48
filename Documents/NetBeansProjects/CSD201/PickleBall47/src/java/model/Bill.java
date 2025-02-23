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
public class Bill {
    private int IDBill;
    private String Invoice;
    private int IDRegisteredPickleBallField;
    private int IDAccount2;
    private Timestamp PaymentDate;
    private double TotalPrice;
    public Bill(int IDBill, String Invoice, int IDRegisteredPickleBallField, int IDAccount2, Timestamp PaymentDate, double TotalPrice) {
        this.IDBill = IDBill;
        this.Invoice = Invoice;
        this.IDRegisteredPickleBallField = IDRegisteredPickleBallField;
        this.IDAccount2 = IDAccount2;
        this.PaymentDate = PaymentDate;
        this.TotalPrice = TotalPrice;
    }
    public int getIDBill() { return IDBill; }
    public void setIDBill(int IDBill) { this.IDBill = IDBill; }

    public String getInvoice() { return Invoice; }
    public void setInvoice(String Invoice) { this.Invoice = Invoice; }

    public int getIDRegisteredPickleBallField() { return IDRegisteredPickleBallField; }
    public void setIDRegisteredPickleBallField(int IDRegisteredPickleBallField) { this.IDRegisteredPickleBallField = IDRegisteredPickleBallField; }

    public int getIDAccount2() { return IDAccount2; }
    public void setIDAccount2(int IDAccount2) { this.IDAccount2 = IDAccount2; }

    public Timestamp getPaymentDate() { return PaymentDate; }
    public void setPaymentDate(Timestamp PaymentDate) { this.PaymentDate = PaymentDate; }

    public double getTotalPrice() { return TotalPrice; }
    public void setTotalPrice(double TotalPrice) { this.TotalPrice = TotalPrice; }

    @Override
    public String toString() {
        return "Bill{" + "IDBill=" + IDBill + ", Invoice=" + Invoice + ", IDRegisteredPickleBallField=" + IDRegisteredPickleBallField + ", IDAccount2=" + IDAccount2 + ", PaymentDate=" + PaymentDate + ", TotalPrice=" + TotalPrice + '}';
    }
    
}