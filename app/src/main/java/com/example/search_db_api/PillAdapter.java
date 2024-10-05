package com.example.search_db_api;

import android.content.Intent;
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

    private List<Pill> pillList;  // 표시할 약물 리스트

    public PillAdapter(List<Pill> pillList) {
        this.pillList = pillList;
    }

    @NonNull
    @Override
    public PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // pill_item 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pill_item, parent, false);
        return new PillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PillViewHolder holder, int position) {
        Pill pill = pillList.get(position);
        holder.itemName.setText(pill.getItemName());
        holder.efcyQesitm.setText(pill.getEfcyQesitm());

        // 이미지 로딩
        Picasso.get().load(pill.getItemImage()).into(holder.itemImage);

        // 리스트의 아이템을 클릭하면 PillDetailsActivity로 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), PillDetailActivity.class);
            intent.putExtra("selectedPill", pill);  // 선택한 약의 데이터를 전달
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return pillList.size();  // 리스트의 아이템 수 반환
    }

    static class PillViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, efcyQesitm;
        ImageView itemImage;

        // ViewHolder 내에서 각 뷰를 초기화
        public PillViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);  // XML에서 정의한 ID
            efcyQesitm = itemView.findViewById(R.id.efcyQesitm);  // 효능 정보
            itemImage = itemView.findViewById(R.id.itemImage);  // 이미지
        }
    }
}
