package com.example.search_db_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;  // 검색 결과를 표시할 RecyclerView
    private PillAdapter pillAdapter;  // RecyclerView의 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // RecyclerView 초기화 및 레이아웃 매니저 설정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 싱글톤을 통해 검색 결과를 가져옴
        List<Pill> searchResults = PillDataStore.getInstance().getPillList();

        // 검색 결과를 어댑터에 전달하고 RecyclerView에 설정
        pillAdapter = new PillAdapter(searchResults);
        recyclerView.setAdapter(pillAdapter);

        // 뒤로 가기 버튼 설정
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());  // 버튼 클릭 시 현재 액티비티 종료
    }
}
