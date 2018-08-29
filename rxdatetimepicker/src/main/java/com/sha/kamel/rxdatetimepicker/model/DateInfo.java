package com.sha.kamel.rxdatetimepicker.model;

import java.util.Date;

/**
 * Created by Sha on 1/27/18.
 */

public class DateInfo {
    private int year;
    private int month;
    private int monthZeroIndexed;
    private int dayOfMonth;
    private Date date;

    public int getYear() {
        return year;
    }

    public DateInfo setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public DateInfo setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getMonthZeroIndexed() {
        return monthZeroIndexed;
    }

    public DateInfo setMonthZeroIndexed(int monthZeroIndexed) {
        this.monthZeroIndexed = monthZeroIndexed;
        return this;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public DateInfo setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public DateInfo setDate(Date date) {
        this.date = date;
        return this;
    }
}
