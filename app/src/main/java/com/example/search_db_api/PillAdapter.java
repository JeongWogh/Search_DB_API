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

    // RecyclerView에 표시할 약물 리스트를 저장하는 변수
    private List<Pill> pillList;

    // 생성자: 표시할 약물 리스트를 받아옴
    public PillAdapter(List<Pill> pillList) {
        this.pillList = pillList;
    }

    @NonNull
    @Override
    public PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // pill_item 레이아웃을 인플레이트하여 ViewHolder를 생성
        // ViewHolder는 각 아이템의 레이아웃을 관리함
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pill_item, parent, false);
        return new PillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PillViewHolder holder, int position) {
        // 현재 위치의 약물 객체를 가져옴
        Pill pill = pillList.get(position);
        // 약물의 이름과 효능 정보를 UI에 설정
        holder.itemName.setText(pill.getItemName());
        holder.efcyQesitm.setText(pill.getEfcyQesitm());

        // Picasso 라이브러리를 사용하여 이미지 URL을 ImageView에 로딩
        Picasso.get().load(pill.getItemImage()).into(holder.itemImage);

        // 리스트의 아이템을 클릭했을 때 이벤트 설정
        holder.itemView.setOnClickListener(v -> {
            // PillDetailActivity로 이동하는 인텐트를 생성
            Intent intent = new Intent(holder.itemView.getContext(), PillDetailActivity.class);
            intent.putExtra("selectedPill", pill);  // 선택된 약물의 데이터를 전달
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pillList.size();  // 약물 리스트의 아이템 수를 반환
    }

    // ViewHolder 클래스: RecyclerView의 각 아이템을 관리
    static class PillViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, efcyQesitm;  // 약물의 이름과 효능 정보를 표시하는 TextView
        ImageView itemImage;  // 약물의 이미지를 표시하는 ImageView

        // 생성자: itemView 내에서 각 뷰를 초기화
        public PillViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);  // 약물 이름 TextView 초기화
            efcyQesitm = itemView.findViewById(R.id.efcyQesitm);  // 약물 효능 TextView 초기화
            itemImage = itemView.findViewById(R.id.itemImage);  // 약물 이미지 ImageView 초기화
        }
    }
}
