package com.sha.kamel.rxdatetimepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.TimePicker;

import com.sha.kamel.rxdatetimepicker.model.TimeInfo;

import java.util.Calendar;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 1/20/18.
 */

public class RxTimePicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private PublishSubject<TimeInfo> ps = PublishSubject.create();

    public static RxTimePicker newInstance() {
        return new RxTimePicker();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(),
                this,
                hourOfDay,
                minute,
                false
        );
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ps.onNext(new TimeInfo(hourOfDay, minute));
        ps.onComplete();
    }

    public Observable<TimeInfo> asObservable(){
        return ps;
    }

    public RxTimePicker show(FragmentManager manager){
        show(manager, getClass().getSimpleName());
        return this;
    }

}