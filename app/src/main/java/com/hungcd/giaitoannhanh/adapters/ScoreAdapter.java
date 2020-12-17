package com.hungcd.giaitoannhanh.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.math.Score;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ItemViewHolder> {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
    private ArrayList<Score> scoreArrayList;
    private Context context;

    public ScoreAdapter(ArrayList<Score> scoreArrayList, Context context) {
        this.scoreArrayList = scoreArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_history_score, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Score score = scoreArrayList.get(position);
        holder.tvScore.setText(score.getScore() + "");
        holder.tvTime.setText(sdf.format(score.getTime()) + "");
    }

    @Override
    public int getItemCount() {
        return scoreArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScore, tvTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}