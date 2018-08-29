

# RxDateTimePicker

## RxJava wrapper for date and time pickers.

|<img src="https://github.com/ShabanKamell/RxDateTimePicker/blob/master/blob/master/raw/date_picker.png" height="500">|<img src="https://github.com/ShabanKamell/RxDateTimePicker/blob/master/blob/master/raw/time_picker.png" height="500">|<img src="https://github.com/ShabanKamell/RxDateTimePicker/blob/master/blob/master/raw/date_time_picker.png" height="500">|

# Installation


[ ![Download](https://api.bintray.com/packages/shabankamel/android/rxdatetimepicker/images/download.svg) ](https://bintray.com/shabankamel/android/rxdatetimepicker/_latestVersion)

```gradle
dependencies {
    implementation 'com.sha.kamel:rx_date_time_picker:1.0.0@aar'
}

allprojects {
 repositories { 
  maven { url "https://dl.bintray.com/shabankamel/android" } 
 }
}
```

# Usage:
## RxDatePicker
```java
RxTimePicker.newInstance()  
        .show(getSupportFragmentManager())  
        .asObservable()  
        .subscribe(timeInfo -> {  
            String msg = timeMessage(timeInfo);  
            tv_date.setText(msg);  
  });
```

## RxTimePicker
```java
RxDatePicker.newInstance()  
        .minDate(System.currentTimeMillis() - (4 * 24 * 60 * 60 * 1000)) // 4 days  
        .maxDate(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000))  
        .show(getSupportFragmentManager())  
        .asObservable()  
        .subscribe(dateInfo -> {  
            String msg = dateMessage(dateInfo);  
            tv_time.setText(msg);  
  });
```

## RxDateTimePicker
```java
RxDateTimePicker.newInstance()  
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
                    tv_dateTime.setText(msg);});
```

### See 'app' module for the full code.

# License

## Apache license 2.0
