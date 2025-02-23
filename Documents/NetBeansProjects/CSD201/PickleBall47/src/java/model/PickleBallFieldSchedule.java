/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;

/**
 *
 * @author Minh Trung
 */
public class PickleBallFieldSchedule {
    private int IDPickleBallFieldSchedule;
    private Time StartTime;
    private Time Endtime;
    private PickleBallField PickleBallField;
    private int Status;

    public PickleBallFieldSchedule(int IDPickleBallFieldSchedule, Time StartTime, Time Endtime, PickleBallField PickleBallField, int Status) {
        this.IDPickleBallFieldSchedule = IDPickleBallFieldSchedule;
        this.StartTime = StartTime;
        this.Endtime = Endtime;
        this.PickleBallField = PickleBallField;
        this.Status = Status;
    }

    public int getIDPickleBallFieldSchedule() {
        return IDPickleBallFieldSchedule;
    }

    public void setIDPickleBallFieldSchedule(int IDPickleBallFieldSchedule) {
        this.IDPickleBallFieldSchedule = IDPickleBallFieldSchedule;
    }

    public Time getStartTime() {
        return StartTime;
    }

    public void setStartTime(Time StartTime) {
        this.StartTime = StartTime;
    }

    public Time getEndtime() {
        return Endtime;
    }

    public void setEndtime(Time Endtime) {
        this.Endtime = Endtime;
    }

    public PickleBallField getPickleBallField() {
        return PickleBallField;
    }

    public void setPickleBallField(PickleBallField PickleBallField) {
        this.PickleBallField = PickleBallField;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "PickleBallFieldSchedule{" + "IDPickleBallFieldSchedule=" + IDPickleBallFieldSchedule + ", StartTime=" + StartTime + ", Endtime=" + Endtime + ", PickleBallField=" + PickleBallField + ", Status=" + Status + '}';
    }
}
