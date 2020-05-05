package com.example.arada.ui.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.arada.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmFragment extends Fragment implements TimePicker.OnTimeChangedListener {
    /*
     * 알람관련 맴버 변수
     */
    // 알람 메니저
    private AlarmManager mManager;
    // 설정 일시
    private GregorianCalendar mCalendar;
    //일자 설정 클래스
    private DatePicker mDate;
    //시작 설정 클래스
    private TimePicker mTime;
    private long nowTime = System.currentTimeMillis();
    /*
     * 통지 관련 맴버 변수
     */
    private NotificationManager mNotification;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alarm, container, false);
        //통지 매니저를 취득
//        mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //알람 매니저를 취득
//        mManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //현재 시각을 취득
        mCalendar = new GregorianCalendar();
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
        //셋 버튼, 리셋버튼의 리스너를 등록
//        setContentView(R.layout.activity_alarm);

        Button b = (Button)root.findViewById(R.id.set);
        b.setOnClickListener (new View.OnClickListener() {
            public void onClick (View v) {
                setAlarm();
            }
        });
        b = (Button)root.findViewById(R.id.reset);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                resetAlarm();
            }
        });
        //일시 설정 클래스로 현재 시각을 설정
        // mDate = (DatePicker)findViewById(R.id.date_picker);
        // mDate.init (mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), this);
        mTime = (TimePicker)root.findViewById(R.id.time_picker);
        mTime.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
        mTime.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
        mTime.setOnTimeChangedListener(this);

        return root;
    }
    private void setAlarm() {
        if(nowTime == mCalendar.getTimeInMillis()){
            Toast.makeText(getActivity(), "혜인짱짱사나이", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(getActivity(), AlarmReceive.class);
        PendingIntent sender = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), 0, sender);

        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }
    //알람의 해제
//    private void resetAlarm() {
//        mManager.cancel(pendingIntent());
//    }
    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
    }
}
