/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Minh Trung
 */
public class PickleBallField {
    private int IDPickleBallField;
    private String Name;
    private int TypeofFootballField;
    private double Price;
    private String Image;
    private int Status;

   public PickleBallField() {
    }
    public PickleBallField(int IDPickleBallField, String Name, int TypeofFootballField, double Price, String Image, int Status) {
        this.IDPickleBallField = IDPickleBallField;
        this.Name = Name;
        this.TypeofFootballField = TypeofFootballField;
        this.Price = Price;
        this.Image = Image;
        this.Status = Status;
    }
    public int getIDPickleBallField() { return IDPickleBallField; }
    public void setIDPickleBallField(int IDPickleBallField) { this.IDPickleBallField = IDPickleBallField; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public int getTypeofFootballField() { return TypeofFootballField; }
    public void setTypeofFootballField(int TypeofFootballField) { this.TypeofFootballField = TypeofFootballField; }

    public double getPrice() { return Price; }
    public void setPrice(double Price) { this.Price = Price; }

    public String getImage() { return Image; }
    public void setImage(String Image) { this.Image = Image; }

    public int getStatus() { return Status; }
    public void setStatus(int Status) { this.Status = Status; }

    @Override
    public String toString() {
        return "PickleBallField{" + "IDPickleBallField=" + IDPickleBallField + ", Name=" + Name + ", TypeofFootballField=" + TypeofFootballField + ", Price=" + Price + ", Image=" + Image + ", Status=" + Status + '}';
    }
    
}
