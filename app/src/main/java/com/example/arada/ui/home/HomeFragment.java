package com.example.arada.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.arada.R;
import com.example.arada.db.RecordDAO;
import com.example.arada.db.RecordVO;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentManager fragmentManager;
    private Fragment fm;

    private Button btn_timer;
    private TextView txt_timer;
    private int mMin = 0;
    private int mMsec = 0;
    private int mSec = 0;

    //상태를 표시하는 '상수' 지정
    //- 각각의 숫자는 독립적인 개별 '상태' 의미
    public static final int INIT = 0;//처음
    public static final int RUN = 1;//실행중
    public static final int PAUSE = 2;//정지
    //상태값을 저장하는 변수
    //- INIT은 초기값임, 그걸 status 안에 넣는다.(0을 넣은거다)
    public static int status = INIT;
    String today;
    Context ct;
    RecordDAO dao = new RecordDAO();

    //타이머 시간 값을 저장할 변수
    public long baseTime,pauseTime =0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ct = container.getContext();
        final TextView txt_today = root.findViewById(R.id.txt_today);
        Init(root);
        //중간에 전화가 오거나 홈 눌렀을때 계속 진행하도록~
        if (savedInstanceState != null){
            if(savedInstanceState.getInt("status")==1){
                baseTime = savedInstanceState.getLong("basetime");
                status = PAUSE;
            }else if(savedInstanceState.getInt("status")==2){
                baseTime = savedInstanceState.getLong("basetime");
                pauseTime = savedInstanceState.getLong("pausetime");
                status =RUN;
            }
            timerManager();
        }
        //오늘날짜 세팅 -> 화면에 뿌려줌
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                today = s;
                txt_today.setText(s);
            }
        });
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerManager();
            }
        });
//        insertRecord();
        return root;
    }
    public void insertRecord(){
        boolean result = dao.search(ct, today);
        if (result){
            dao.update(ct, txt_timer.toString(), today);
        }else{
            RecordVO vo = new RecordVO();
            vo.set_date(today);
            vo.setRecord(txt_timer.toString());
            dao.insert(ct, vo);
        }
    }
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if(status==PAUSE){
            outState.putInt("status",2);
            outState.putLong("pauseTime", pauseTime);
            outState.putLong("baseTime",baseTime);
        }else if(status == RUN){
            outState.putInt("status",1);
            outState.putLong("baseTime",baseTime);
        }

    }
    private void Init(View root) {
        btn_timer = root.findViewById(R.id.btn_timer);
        txt_timer = root.findViewById(R.id.txt_timer);
    }

    private void timerManager(){
        switch (status){
            case INIT:
                //어플리케이션이 실행되고 나서 실제로 경과된 시간...
                baseTime = SystemClock.elapsedRealtime();
                //핸들러 실행
                handler.sendEmptyMessage(0);
                btn_timer.setText("STOP");
                status = RUN;
                break;
            case RUN:
                //핸들러 정지
                handler.removeMessages(0);
                //시간체크
                pauseTime = SystemClock.elapsedRealtime();
                btn_timer.setText("START");
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);
                handler.sendEmptyMessage(0);
                btn_timer.setText("STOP");
                status = RUN;
        }
    }

    private String getTime(){
        //경과된 시간 체크
        long nowTime = SystemClock.elapsedRealtime();
        //시스템이 부팅된 이후의 시간?
        long overTime = nowTime - baseTime;
        //overTiem/1000 ->초
        long h = overTime/1000/3600;    //시단위 계산
        long m = overTime/1000/60;      //분단위 계산
        long s = (overTime/1000)%60;    //초단위 계산
        String recTime = String.format("%02d:%02d:%02d",h,m,s);
        return recTime;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            txt_timer.setText(getTime());
            handler.sendEmptyMessage(0);
        }
    };
}

