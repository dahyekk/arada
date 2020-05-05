package com.example.arada.ui.calendar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.arada.ArrayAdapter;
import com.example.arada.R;
import com.example.arada.db.ListViewAdapter;
import com.example.arada.db.MemoDAO;
import com.example.arada.db.RecordDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class CalendarFragment extends Fragment {
    Context ct;
    View root;
    private final String fileName = "items.list";

    private ListView listview;
    private ArrayAdapter adapter;
    private ArrayList<String> items = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_calendar, container, false);
        ct = container.getContext();

        Button btn_insert = root.findViewById(R.id.schInsert);
        Button btn_update = root.findViewById(R.id.schUpdate);
        Button btn_delete = root.findViewById(R.id.schDel);
        dataLoding();

        final CalendarView mCalendarView =  root.findViewById(R.id.calendar);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() // 날짜 선택 이벤트
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                String date = year + "." + (month + 1) + "." + dayOfMonth;
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.navigation_memo, new FragmentWeb())
//                        .addToBackStack(null)
//                        .commit();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return root;
    }

    void dataLoding() {
        ArrayList<HashMap<String, String>> list = new MemoDAO().selectAll(ct);
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.setList(list);
        ListView lv = root.findViewById(R.id.sch_list);
        lv.setAdapter(adapter);
    }

}
