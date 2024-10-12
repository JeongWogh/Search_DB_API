package com.example.search_db_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private EditText searchInput;
    private CheckBox coldCheckBox, indigestionCheckBox, feverCheckBox, headacheCheckBox, dizzinessCheckBox, insomniaCheckBox,
            fatigueCheckBox;
    private RecentSearchesManager recentSearchesManager;
    private RecyclerView recentSearchesRecyclerView;
    private RecentSearchesAdapter recentSearchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 컴포넌트 초기화
        initializeUIComponents();

        // 최근 검색어 관리 객체 초기화
        recentSearchesManager = new RecentSearchesManager(this);

        // API 클라이언트 생성
        apiService = ApiClient.getClient().create(ApiService.class);

        // 검색 버튼 클릭 리스너 설정
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> searchPills());

        // 최근 검색어 RecyclerView 설정
        setupRecentSearchesRecyclerView();
    }

    private void initializeUIComponents() {
        searchInput = findViewById(R.id.searchInput);
        coldCheckBox = findViewById(R.id.coldCheckBox);
        indigestionCheckBox = findViewById(R.id.indigestionCheckBox);
        feverCheckBox = findViewById(R.id.feverCheckBox);
        headacheCheckBox = findViewById(R.id.headacheCheckBox);
        dizzinessCheckBox = findViewById(R.id.dizzinessCheckBox);
        insomniaCheckBox = findViewById(R.id.insomniaCheckBox);
        fatigueCheckBox = findViewById(R.id.fatigueCheckBox);
        recentSearchesRecyclerView = findViewById(R.id.recentSearchesRecyclerView);
    }

    private void setupRecentSearchesRecyclerView() {
        recentSearchesAdapter = new RecentSearchesAdapter(recentSearchesManager.getRecentSearches(), this::onRecentSearchClick);
        recentSearchesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentSearchesRecyclerView.setAdapter(recentSearchesAdapter);
    }

    private void onRecentSearchClick(String query) {
        searchInput.setText(query);
        searchPills();
    }

    private void searchPills() {
        String query = searchInput.getText().toString();
        List<String> selectedSymptoms = getSelectedSymptoms();

        if (!query.isEmpty()) {
            // 최근 검색어에 추가
            recentSearchesManager.addSearchQuery(query);
            // RecyclerView 업데이트
            recentSearchesAdapter.updateRecentSearches(recentSearchesManager.getRecentSearches());
        }

        Call<List<Pill>> call = apiService.searchPills(query, selectedSymptoms);
        call.enqueue(new Callback<List<Pill>>() {
            @Override
            public void onResponse(Call<List<Pill>> call, Response<List<Pill>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pill> searchResults = response.body();
                    if (!searchResults.isEmpty()) {
                        PillDataStore.getInstance().setPillList(searchResults);
                        Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pill>> call, Throwable t) {
                Log.e("SearchPills", "Network request failed", t);
                Toast.makeText(MainActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getSelectedSymptoms() {
        List<String> selectedSymptoms = new ArrayList<>();
        if (coldCheckBox.isChecked()) selectedSymptoms.add("감기");
        if (indigestionCheckBox.isChecked()) selectedSymptoms.add("소화불량");
        if (feverCheckBox.isChecked()) selectedSymptoms.add("발열");
        if (headacheCheckBox.isChecked()) selectedSymptoms.add("두통");
        if (dizzinessCheckBox.isChecked()) selectedSymptoms.add("어지러움");
        if (insomniaCheckBox.isChecked()) selectedSymptoms.add("불면증");
        if (fatigueCheckBox.isChecked()) selectedSymptoms.add("피로");
        return selectedSymptoms;
    }
}