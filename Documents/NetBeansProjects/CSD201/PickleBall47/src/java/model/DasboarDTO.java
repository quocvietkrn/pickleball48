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
public class DasboarDTO {
    
    String year;
    String month;
    String revenue;

    public DasboarDTO() {
    }

    public DasboarDTO(String year, String month, String revenue) {
        this.year = year;
        this.month = month;
        this.revenue = revenue;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
    
    
}
