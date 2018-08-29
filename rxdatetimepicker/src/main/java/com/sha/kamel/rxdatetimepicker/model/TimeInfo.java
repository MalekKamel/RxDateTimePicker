package com.sha.kamel.rxdatetimepicker.model;

/**
 * Created by Sha on 1/27/18.
 */

public class TimeInfo {
    private int hourOfDay;
    private int minute;

    public TimeInfo() {
    }

    public TimeInfo(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public TimeInfo setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
        return this;
    }

    public int getMinute() {
        return minute;
    }

    public TimeInfo setMinute(int minute) {
        this.minute = minute;
        return this;
    }
}
