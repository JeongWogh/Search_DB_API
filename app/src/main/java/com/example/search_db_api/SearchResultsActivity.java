package com.example.search_db_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PillAdapter pillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 싱글톤에서 검색 결과 가져오기
        List<Pill> searchResults = PillDataStore.getInstance().getPillList();

        // 검색 결과를 어댑터에 전달하고 RecyclerView에 설정
        pillAdapter = new PillAdapter(searchResults);
        recyclerView.setAdapter(pillAdapter);

        // 뒤로 가기 버튼
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }
}
