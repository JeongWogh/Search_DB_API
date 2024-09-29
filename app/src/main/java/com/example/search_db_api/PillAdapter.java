package com.example.search_db_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.PillViewHolder> {

    private List<Pill> pillList;

    public PillAdapter(List<Pill> pillList) {
        this.pillList = pillList;
    }

    @NonNull
    @Override
    public PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pill_item, parent, false);
        return new PillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PillViewHolder holder, int position) {
        Pill pill = pillList.get(position);
        holder.itemName.setText(pill.getItemName());
        holder.efcyQesitm.setText(pill.getEfcyQesitm());

        // 이미지 로딩 (Picasso 사용)
        Picasso.get().load(pill.getItemImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return pillList.size();
    }

    static class PillViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, efcyQesitm;
        ImageView itemImage;

        public PillViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            efcyQesitm = itemView.findViewById(R.id.efcyQesitm);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }

    public void updateData(List<Pill> newPillList) {
        this.pillList = newPillList;
        notifyDataSetChanged();
    }

}