package com.example.search_db_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentSearchesAdapter extends RecyclerView.Adapter<RecentSearchesAdapter.ViewHolder> {

    // 최근 검색어 리스트를 저장하는 변수
    private List<String> recentSearches;
    // 아이템 클릭 시 호출될 리스너 인터페이스
    private OnItemClickListener listener;

    // 아이템 클릭 리스너 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(String item);  // 아이템이 클릭될 때 호출될 메서드
    }

    // 생성자: 최근 검색어 리스트와 클릭 리스너를 받아 초기화
    public RecentSearchesAdapter(List<String> recentSearches, OnItemClickListener listener) {
        this.recentSearches = recentSearches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // simple_list_item_1 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 현재 위치의 검색어를 가져옴
        String item = recentSearches.get(position);
        // 검색어를 TextView에 설정
        holder.textView.setText(item);
        // 아이템 클릭 시 리스너 호출
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return recentSearches.size();  // 검색어 리스트의 크기를 반환
    }

    // 새로운 최근 검색어 리스트로 업데이트하는 메서드
    public void updateRecentSearches(List<String> newRecentSearches) {
        this.recentSearches = newRecentSearches;  // 리스트 업데이트
        notifyDataSetChanged();  // 데이터 변경 알림 (UI 갱신)
    }

    // ViewHolder 클래스: RecyclerView의 각 아이템을 관리
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;  // 검색어를 표시할 TextView

        ViewHolder(View itemView) {
            super(itemView);
            // simple_list_item_1 레이아웃의 TextView를 초기화
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
