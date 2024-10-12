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

    // API 서비스와 UI 컴포넌트 변수 선언
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

    // UI 컴포넌트를 초기화하는 메서드
    private void initializeUIComponents() {
        searchInput = findViewById(R.id.searchInput); // 검색어 입력 필드
        coldCheckBox = findViewById(R.id.coldCheckBox); // 감기 체크박스
        indigestionCheckBox = findViewById(R.id.indigestionCheckBox); // 소화불량 체크박스
        feverCheckBox = findViewById(R.id.feverCheckBox); // 발열 체크박스
        headacheCheckBox = findViewById(R.id.headacheCheckBox); // 두통 체크박스
        dizzinessCheckBox = findViewById(R.id.dizzinessCheckBox); // 어지러움 체크박스
        insomniaCheckBox = findViewById(R.id.insomniaCheckBox); // 불면증 체크박스
        fatigueCheckBox = findViewById(R.id.fatigueCheckBox); // 피로 체크박스
        recentSearchesRecyclerView = findViewById(R.id.recentSearchesRecyclerView); // 최근 검색어 표시용 RecyclerView
    }

    // 최근 검색어 RecyclerView를 설정하는 메서드
    private void setupRecentSearchesRecyclerView() {
        // 최근 검색어 어댑터 초기화 및 RecyclerView에 설정
        recentSearchesAdapter = new RecentSearchesAdapter(recentSearchesManager.getRecentSearches(), this::onRecentSearchClick);
        recentSearchesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentSearchesRecyclerView.setAdapter(recentSearchesAdapter);
    }

    // 최근 검색어를 클릭했을 때 실행되는 메서드
    private void onRecentSearchClick(String query) {
        searchInput.setText(query); // 클릭한 검색어를 입력 필드에 설정
        searchPills(); // 검색 실행
    }

    // 약물 검색을 수행하는 메서드
    private void searchPills() {
        String query = searchInput.getText().toString(); // 검색어 가져오기
        List<String> selectedSymptoms = getSelectedSymptoms(); // 선택된 증상 목록 가져오기

        // 검색어가 비어 있지 않은 경우에만 최근 검색어에 추가
        if (!query.isEmpty()) {
            recentSearchesManager.addSearchQuery(query); // 최근 검색어에 추가
            recentSearchesAdapter.updateRecentSearches(recentSearchesManager.getRecentSearches()); // RecyclerView 업데이트
        }

        // Retrofit API 호출
        Call<List<Pill>> call = apiService.searchPills(query, selectedSymptoms);
        call.enqueue(new Callback<List<Pill>>() {
            @Override
            public void onResponse(Call<List<Pill>> call, Response<List<Pill>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pill> searchResults = response.body(); // 검색 결과 받아오기
                    if (!searchResults.isEmpty()) {
                        PillDataStore.getInstance().setPillList(searchResults); // 검색 결과를 데이터 저장소에 저장
                        Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class); // 검색 결과 화면으로 이동
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show(); // 검색 결과 없음 알림
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show(); // 에러 메시지 표시
                }
            }

            @Override
            public void onFailure(Call<List<Pill>> call, Throwable t) {
                Log.e("SearchPills", "Network request failed", t); // 네트워크 요청 실패 로그
                Toast.makeText(MainActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show(); // 네트워크 오류 알림
            }
        });
    }

    // 선택된 증상 목록을 가져오는 메서드
    private List<String> getSelectedSymptoms() {
        List<String> selectedSymptoms = new ArrayList<>();
        if (coldCheckBox.isChecked()) selectedSymptoms.add("감기");
        if (indigestionCheckBox.isChecked()) selectedSymptoms.add("소화불량");
        if (feverCheckBox.isChecked()) selectedSymptoms.add("발열");
        if (headacheCheckBox.isChecked()) selectedSymptoms.add("두통");
        if (dizzinessCheckBox.isChecked()) selectedSymptoms.add("어지러움");
        if (insomniaCheckBox.isChecked()) selectedSymptoms.add("불면증");
        if (fatigueCheckBox.isChecked()) selectedSymptoms.add("피로");
        return selectedSymptoms; // 선택된 증상을 리스트로 반환
    }
}
