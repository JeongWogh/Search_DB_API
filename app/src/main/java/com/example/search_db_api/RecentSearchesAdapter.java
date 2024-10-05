package com.example.search_db_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentSearchesAdapter extends RecyclerView.Adapter<RecentSearchesAdapter.ViewHolder> {

    private List<String> recentSearches;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public RecentSearchesAdapter(List<String> recentSearches, OnItemClickListener listener) {
        this.recentSearches = recentSearches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = recentSearches.get(position);
        holder.textView.setText(item);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return recentSearches.size();
    }

    public void updateRecentSearches(List<String> newRecentSearches) {
        this.recentSearches = newRecentSearches;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}