package com.sha.kamel.rxdatetimepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sha.kamel.rxdatetimepicker.model.DateInfo;
import com.sha.kamel.rxdatetimepicker.model.DateTimeInfo;
import com.sha.kamel.rxdatetimepicker.model.TimeInfo;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxDateTimePicker extends DialogFragment {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button btn_select;
    private Button btn_dismiss;

    private long maxDate;
    private long minDate;

    private TimeInfo timeInfo = new TimeInfo();
    private DateTimeInfo dateTimeInfo = new DateTimeInfo();

    private PublishSubject<DateTimeInfo> ps = PublishSubject.create();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dialog_date_time_picker,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        bindUi();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void bindViews() {
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        timePicker = findViewById(R.id.timePicker);
        btn_select = findViewById(R.id.btn_select);
        btn_dismiss = findViewById(R.id.btn_dismiss);

        if (minDate > 0) datePicker.setMinDate(minDate);
        if (maxDate > 0) datePicker.setMaxDate(maxDate);

        btn_select.setOnClickListener(v -> {
            set();
            dismiss();
        });

        btn_dismiss.setOnClickListener(v -> dismiss());
    }

    protected void bindUi() {
        final Calendar c = Calendar.getInstance();
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);

        timeInfo.setMinute(m)
                .setHourOfDay(h);

        timePicker.setCurrentHour(h);
        timePicker.setCurrentMinute(m);

        dateTimeInfo.setTimeInfo(timeInfo);

        timePicker.setOnTimeChangedListener((timePicker, hourOfDay, minute) -> {
            timeInfo.setMinute(hourOfDay)
                    .setHourOfDay(minute);
        });
    }

    private <T extends View> T findViewById(int id) {
        return getView().findViewById(id);
    }

    // Logic here ------------------------------

    public static RxDateTimePicker newInstance() {
        return new RxDateTimePicker();
    }

    private void set() {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, datePicker.getYear());
        c.set(Calendar.MONTH, datePicker.getMonth());
        c.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.SECOND, 0);


        dateTimeInfo.setDateInfo(
                new DateInfo()
                        .setYear(datePicker.getYear())
                        .setMonth(datePicker.getMonth() + 1)
                        .setMonthZeroIndexed(datePicker.getMonth())
                        .setDayOfMonth(datePicker.getDayOfMonth())
                        .setDate(c.getTime())
        );

        ps.onNext(dateTimeInfo);
        ps.onComplete();
    }

    public Observable<DateTimeInfo> asObservable(){
        return ps;
    }

    public RxDateTimePicker show(FragmentManager manager){
        show(manager, getClass().getSimpleName());
        return this;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }

    public Button getSelectButton() {
        return btn_select;
    }

    public Button getDismissButton() {
        return btn_dismiss;
    }

    public RxDateTimePicker maxDate(long max){
        this.maxDate = max;
        return this;
    }

    public RxDateTimePicker minDate(long min){
        this.minDate = min;
        return this;
    }

}
