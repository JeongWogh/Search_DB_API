package com.example.search_db_api;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PillDetailActivity extends AppCompatActivity {

    private Pill pill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_details);

        Log.d("PillDetailActivity", "Activity 시작됨");

        // 인텐트에서 Pill 객체 가져오기
        pill = getIntent().getParcelableExtra("selectedPill");
        if (pill == null) {
            Log.e("PillDetailActivity", "Pill 객체가 null입니다. 인텐트 데이터가 전달되지 않았습니다.");
            Toast.makeText(this, "약 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        } else {
            Log.d("PillDetailActivity", "Pill 객체가 정상적으로 로드됨: " + pill.getItemName());
        }

        // UI 요소 찾기
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView efcyQesitmTextView = findViewById(R.id.efcyQesitmTextView);
        TextView atpnQesitmTextView = findViewById(R.id.atpnQesitmTextView);
        TextView seQesitmTextView = findViewById(R.id.seQesitmTextView);
        TextView etcotcTextView = findViewById(R.id.etcotcTextView);
        ImageView itemImageView = findViewById(R.id.itemImageView);
        Button addMedicationButton = findViewById(R.id.button_add_medication);

        // UI에 데이터 설정
        itemNameTextView.setText(pill.getItemName());
        efcyQesitmTextView.setText("효능: " + pill.getEfcyQesitm());
        atpnQesitmTextView.setText("주의사항: " + pill.getAtpnQesitm());
        seQesitmTextView.setText("부작용: " + pill.getSeQesitm());
        etcotcTextView.setText("분류: " + pill.getEtcotc());
        Log.d("PillDetailActivity", "UI 요소 설정 완료");

        // 이미지 로딩
        Picasso.get().load(pill.getItemImage()).into(itemImageView);
        Log.d("PillDetailActivity", "이미지 로드 완료");

        // 복용 중인 약 추가 버튼 클릭 리스너 설정
        addMedicationButton.setOnClickListener(v -> addMedicationToDatabase());
    }

    private void addMedicationToDatabase() {
        // Retrofit API 호출
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ResponseBody> call = apiService.addPill(pill);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "약물이 성공적으로 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.d("PillDetailActivity", "약물 추가 성공");
                } else {
                    Toast.makeText(getApplicationContext(), "약물 추가 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("PillDetailActivity", "약물 추가 실패: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("PillDetailActivity", "서버 오류: " + t.getMessage(), t);
            }
        });
    }
}
