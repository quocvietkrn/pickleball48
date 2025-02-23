/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Minh Trung
 */
public class RegisteredPickleBallField {
    private int IDRegisteredPickleBallField;
    private Account Account1;
    private Account Account2;
    private PickleBallFieldSchedule PickleBallFieldSchedule;
    private Date Date;
    private String Name;
    private String PhoneNumber;
    private double Deposit;
    private int Status;
    private String Note;

    public RegisteredPickleBallField(int IDRegisteredPickleBallField, Account Account1, Account Account2, PickleBallFieldSchedule PickleBallFieldSchedule, Date Date, String Name, String PhoneNumber, double Deposit, int Status, String Note) {
        this.IDRegisteredPickleBallField = IDRegisteredPickleBallField;
        this.Account1 = Account1;
        this.Account2 = Account2;
        this.PickleBallFieldSchedule = PickleBallFieldSchedule;
        this.Date = Date;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Deposit = Deposit;
        this.Status = Status;
        this.Note = Note;
    }

    public int getIDRegisteredPickleBallField() {
        return IDRegisteredPickleBallField;
    }

    public void setIDRegisteredPickleBallField(int IDRegisteredPickleBallField) {
        this.IDRegisteredPickleBallField = IDRegisteredPickleBallField;
    }

    public Account getAccount1() {
        return Account1;
    }

    public void setAccount1(Account Account1) {
        this.Account1 = Account1;
    }

    public Account getAccount2() {
        return Account2;
    }

    public void setAccount2(Account Account2) {
        this.Account2 = Account2;
    }

    public PickleBallFieldSchedule getPickleBallFieldSchedule() {
        return PickleBallFieldSchedule;
    }

    public void setPickleBallFieldSchedule(PickleBallFieldSchedule PickleBallFieldSchedule) {
        this.PickleBallFieldSchedule = PickleBallFieldSchedule;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public double getDeposit() {
        return Deposit;
    }

    public void setDeposit(double Deposit) {
        this.Deposit = Deposit;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    @Override
    public String toString() {
        return "RegisteredPickleBallField{" + "IDRegisteredPickleBallField=" + IDRegisteredPickleBallField + ", Account1=" + Account1 + ", Account2=" + Account2 + ", PickleBallFieldSchedule=" + PickleBallFieldSchedule + ", Date=" + Date + ", Name=" + Name + ", PhoneNumber=" + PhoneNumber + ", Deposit=" + Deposit + ", Status=" + Status + ", Note=" + Note + '}';
    }

    

  
}