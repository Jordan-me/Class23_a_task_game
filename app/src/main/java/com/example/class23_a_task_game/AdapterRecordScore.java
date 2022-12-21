package com.example.class23_a_task_game;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class AdapterRecordScore<recordItemClickListener> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private ArrayList<RecordScore> records ;
    private RecordItemClickListener recordItemClickListener;

    public interface RecordItemClickListener {
        void recordItemClicked(RecordScore item, String lat, String lng);
    }
    public AdapterRecordScore(Activity activity, ArrayList<RecordScore> records) {
        this.activity = activity;
        this.records = records;
    }

    public AdapterRecordScore setRecordItemClickListener(RecordItemClickListener recordItemClickListener) {
        this.recordItemClickListener = recordItemClickListener;
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_record, viewGroup, false);
        return new RecordScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecordScoreViewHolder recordViewHolder = (RecordScoreViewHolder) holder;
        RecordScore record = getItem(position);
        recordViewHolder.record_LBL_score.setText("" + record.getScore());
        recordViewHolder.record_LBL_lat.setText(record.getLat());
        recordViewHolder.record_LBL_lng.setText(record.getLng());

    }

    @Override
    public int getItemCount() {
        if (records==null){
            return 0;
        }
        return records.size();
    }

    private RecordScore getItem(int position) {
        return records.get(position);
    }

    public class RecordScoreViewHolder extends RecyclerView.ViewHolder {
        public MaterialTextView record_LBL_score;
        public MaterialTextView record_LBL_lat;
        public MaterialTextView record_LBL_lng;


        public RecordScoreViewHolder(final View itemView) {
            super(itemView);
            this.record_LBL_lng = itemView.findViewById(R.id.record_LBL_lng);
            this.record_LBL_lat = itemView.findViewById(R.id.record_LBL_lat);
            this.record_LBL_score = itemView.findViewById(R.id.record_LBL_score);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("pttt", "position= " + getItem(getAdapterPosition()).getLng()+getItem(getAdapterPosition()).getLng());

                    recordItemClickListener.recordItemClicked(getItem(getAdapterPosition()), getItem(getAdapterPosition()).getLat(),getItem(getAdapterPosition()).getLng());
                }
            });


        }
    }
}
