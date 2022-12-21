package com.example.class23_a_task_game;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    private RecyclerView main_LST_records;
    private CallBack_List callBackList;
    private AppCompatActivity context;

    public void setActivity(AppCompatActivity activity) {
        this.context = activity;
    }

    public void setCallBackList(CallBack_List callBackList) {
        this.callBackList = callBackList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        findViews(view);

        return view;
    }


    private void findViews(View view) {
        main_LST_records = view.findViewById(R.id.main_LST_records);
    }

    public RecyclerView getMain_LST_records() {
        return main_LST_records;
    }
}
