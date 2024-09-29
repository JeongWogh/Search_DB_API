package com.example.search_db_api;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchInput;
    private CheckBox coldCheckBox, indigestionCheckBox, feverCheckBox;
    private CheckBox headacheCheckBox, dizzinessCheckBox, nauseaCheckBox, insomniaCheckBox, fatigueCheckBox;
    private Button searchButton;
    private RecyclerView recyclerView;
    private PillAdapter pillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.searchInput);
        coldCheckBox = findViewById(R.id.coldCheckBox);
        indigestionCheckBox = findViewById(R.id.indigestionCheckBox);
        feverCheckBox = findViewById(R.id.feverCheckBox);
        headacheCheckBox = findViewById(R.id.headacheCheckBox);
        dizzinessCheckBox = findViewById(R.id.dizzinessCheckBox);
        nauseaCheckBox = findViewById(R.id.nauseaCheckBox);
        insomniaCheckBox = findViewById(R.id.insomniaCheckBox);
        fatigueCheckBox = findViewById(R.id.fatigueCheckBox);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchText = searchInput.getText().toString();
        List<String> selectedSymptoms = new ArrayList<>();

        if (coldCheckBox.isChecked()) {
            selectedSymptoms.add("감기");
        }
        if (indigestionCheckBox.isChecked()) {
            selectedSymptoms.add("소화불량");
        }
        if (feverCheckBox.isChecked()) {
            selectedSymptoms.add("발열");
        }
        if (headacheCheckBox.isChecked()) {
            selectedSymptoms.add("두통");
        }
        if (dizzinessCheckBox.isChecked()) {
            selectedSymptoms.add("어지러움");
        }
        if (nauseaCheckBox.isChecked()) {
            selectedSymptoms.add("메스꺼움");
        }
        if (insomniaCheckBox.isChecked()) {
            selectedSymptoms.add("불면증");
        }
        if (fatigueCheckBox.isChecked()) {
            selectedSymptoms.add("피로");
        }

        // 검색을 수행하는 API 호출
        ApiService apiService = ApiClient.getApiService();
        Call<List<Pill>> call = apiService.searchPills(searchText, selectedSymptoms);

        call.enqueue(new Callback<List<Pill>>() {
            @Override
            public void onResponse(Call<List<Pill>> call, Response<List<Pill>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pill> pillList = response.body();
                    pillAdapter = new PillAdapter(pillList);
                    recyclerView.setAdapter(pillAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pill>> call, Throwable t) {
                Log.e("MainActivity", "Error fetching pills", t);
                Toast.makeText(MainActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
