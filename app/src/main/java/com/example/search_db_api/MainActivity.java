package com.example.search_db_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private CheckBox coldCheckBox, indigestionCheckBox, feverCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.searchInput);
        coldCheckBox = findViewById(R.id.coldCheckBox);
        indigestionCheckBox = findViewById(R.id.indigestionCheckBox);
        feverCheckBox = findViewById(R.id.feverCheckBox);

        apiService = ApiClient.getClient().create(ApiService.class);

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> searchPills());
    }

    private void searchPills() {
        String query = searchInput.getText().toString();
        List<String> selectedSymptoms = getSelectedSymptoms();

        Call<List<Pill>> call = apiService.searchPills(query, selectedSymptoms);
        call.enqueue(new Callback<List<Pill>>() {
            @Override
            public void onResponse(Call<List<Pill>> call, Response<List<Pill>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pill> searchResults = response.body();

                    // 검색 결과를 싱글톤에 저장
                    PillDataStore.getInstance().setPillList(searchResults);

                    // SearchResultsActivity로 이동
                    Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pill>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getSelectedSymptoms() {
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
        return selectedSymptoms;
    }
}
