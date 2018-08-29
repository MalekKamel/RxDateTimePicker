package com.sha.kamel.rxdatesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.sha.kamel.rxdatetimepicker.RxDatePicker;
import com.sha.kamel.rxdatetimepicker.RxDateTimePicker;
import com.sha.kamel.rxdatetimepicker.RxTimePicker;
import com.sha.kamel.rxdatetimepicker.model.DateInfo;
import com.sha.kamel.rxdatetimepicker.model.TimeInfo;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Button btn_date;
    private Button btn_time;
    private Button btn_dateTime;
    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        pickDate();
        pickTime();
        pickDateTime();
    }

    private void bindViews() {
        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        btn_dateTime = findViewById(R.id.btn_dateTime);
        tv_date = findViewById(R.id.tv_date);
        tv_dateTime = findViewById(R.id.tv_dateTime);
        tv_time = findViewById(R.id.tv_time);
    }

    private void pickDate() {
        btn_date.setOnClickListener(v -> showDateDialog());
    }

    private void pickTime() {
        btn_time.setOnClickListener(v -> showTimeDialog());
    }

    private void pickDateTime() {
        btn_dateTime.setOnClickListener(v -> showDateTimeDialog());
    }

    private void showDateDialog() {
        Disposable disposable = RxTimePicker.newInstance()
                .show(getSupportFragmentManager())
                .asObservable()
                .subscribe(timeInfo -> {
                    String msg = timeMessage(timeInfo);
                    tv_date.setText(msg);
                });
        compositeDisposable.add(disposable);
    }

    private void showTimeDialog() {
        Disposable disposable = RxDatePicker.newInstance()
                .minDate(System.currentTimeMillis() - (4 * 24 * 60 * 60 * 1000)) // 4 days
                .maxDate(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000))
                .show(getSupportFragmentManager())
                .asObservable()
                .subscribe(dateInfo -> {
                    String msg = dateMessage(dateInfo);
                    tv_time.setText(msg);
                });
        compositeDisposable.add(disposable);
    }

    private void showDateTimeDialog() {
        Disposable disposable =  RxDateTimePicker.newInstance()
                .minDate(System.currentTimeMillis() - (4 * 24 * 60 * 60 * 1000)) // 4 days
                .maxDate(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000))
                .show(getSupportFragmentManager())
                .asObservable()
                .subscribe(dateTimeInfo -> {
                            String msg = new StringBuilder()
                                    .append(dateMessage(dateTimeInfo.getDateInfo()))
                                    .append("\n")
                                    .append(timeMessage(dateTimeInfo.getTimeInfo()))
                                    .toString();
                            tv_dateTime.setText(msg);
                        }
                );
        compositeDisposable.add(disposable);
    }

    private String timeMessage(TimeInfo timeInfo) {
        int hourOfDay = timeInfo.getHourOfDay();
        int minute = timeInfo.getMinute();
        return new StringBuilder()
                .append("hourOfDay: ")
                .append(hourOfDay)
                .append("\n")
                .append("minute: ")
                .append(minute)
                .toString();
    }

    private String dateMessage(DateInfo dateInfo) {
        String date = dateInfo.getDate().toString();
        int year = dateInfo.getYear();
        int month = dateInfo.getMonth();
        int monthZeroIndexed = dateInfo.getMonthZeroIndexed();
        int dayOfMonth = dateInfo.getDayOfMonth();

        return new StringBuilder()
                .append("date: ")
                .append(date)
                .append("\n")
                .append("year: ")
                .append(year)
                .append("\n")
                .append("month: ")
                .append(month)
                .append("\n")
                .append("monthZeroIndexed: ")
                .append(monthZeroIndexed)
                .append("\n")
                .append("dayOfMonth: ")
                .append(dayOfMonth)
                .toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
