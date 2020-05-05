package com.example.arada.ui.record;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.arada.R;
import com.example.arada.db.ListViewAdapter;
import com.example.arada.db.RecordDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordFragment extends Fragment {
    Context ct;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record, container, false);
        ct = container.getContext();
        ArrayList<HashMap<String, String>> list = new RecordDAO().selectAll(ct);
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.setList(list);
        ListView lv =root.findViewById(R.id.record_list);
        lv.setAdapter(adapter);
        return root;
    }
}
