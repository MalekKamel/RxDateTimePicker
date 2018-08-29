package com.sha.kamel.rxdatetimepicker.model;


public class DateTimeInfo {
    private DateInfo dateInfo;
    private TimeInfo timeInfo;

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public DateTimeInfo setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
        return this;
    }

    public TimeInfo getTimeInfo() {
        return timeInfo;
    }

    public DateTimeInfo setTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
        return this;
    }
}
