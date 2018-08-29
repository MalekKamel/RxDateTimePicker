package com.sha.kamel.rxdatetimepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import com.sha.kamel.rxdatetimepicker.model.DateInfo;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 1/20/18.
 */

public class RxDatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private PublishSubject<DateInfo> ps = PublishSubject.create();
    private long max;
    private long min;

    public static RxDatePicker newInstance() {
        return new RxDatePicker();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        if (min > 0) dialog.getDatePicker().setMinDate(min);
        if (max > 0) dialog.getDatePicker().setMaxDate(max);
        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.SECOND, 0);

        ps.onNext(
                new DateInfo()
                        .setYear(year)
                        .setMonth(month + 1)
                        .setMonthZeroIndexed(month)
                        .setDayOfMonth(day)
                        .setDate(c.getTime())
        );
        ps.onComplete();
    }

    public RxDatePicker maxDate(long max){
        this.max = max;
        return this;
    }

    public RxDatePicker minDate(long min){
        this.min = min;
        return this;
    }

    public Observable<DateInfo> asObservable(){
        return ps;
    }

    public RxDatePicker show(FragmentManager manager){
        show(manager, getClass().getSimpleName());
        return this;
    }

}