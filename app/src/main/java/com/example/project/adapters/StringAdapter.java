package com.example.project.adapters;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {
    private List<String> items;
    private OnItemClickListener listener;

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(String item, int position);
    }

    // Constructor with listener
    public StringAdapter(List<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override // Wraps the view in a holder so it can be reused later
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(40, 40, 40, 40);
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.parseColor("#CC000000"));
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.textView.setText(item);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(item, position);
            }
        });
    }

    // Returns number of items
    @Override
    public int getItemCount() { return items.size(); }

    // Finds the view field and saves it into the textView
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(TextView itemView) {
            super(itemView);
            this.textView = (TextView) itemView;
        }
    }
}
