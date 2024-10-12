package com.example.search_db_api;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PillDetailActivity extends AppCompatActivity {

    private Pill pill;  // 전달받은 약물 객체를 저장하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_details);

        // 인텐트에서 전달된 Pill 객체 가져오기
        pill = getIntent().getParcelableExtra("selectedPill");
        if (pill == null) {
            Log.e("PillDetailActivity", "Pill 객체가 null입니다. 인텐트 데이터가 전달되지 않았습니다.");
            Toast.makeText(this, "약 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
            finish();  // 객체가 null인 경우, 액티비티 종료
            return;
        }

        // UI 요소 찾기 (각 약물의 정보를 표시할 뷰들)
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView efcyQesitmTextView = findViewById(R.id.efcyQesitmTextView);
        TextView atpnQesitmTextView = findViewById(R.id.atpnQesitmTextView);
        TextView seQesitmTextView = findViewById(R.id.seQesitmTextView);
        TextView etcotcTextView = findViewById(R.id.etcotcTextView);
        ImageView itemImageView = findViewById(R.id.itemImageView);
        Button addMedicationButton = findViewById(R.id.button_add_medication);

        // UI에 데이터 설정 (Pill 객체의 정보를 뷰에 설정)
        itemNameTextView.setText(pill.getItemName());
        efcyQesitmTextView.setText("효능: " + pill.getEfcyQesitm());
        atpnQesitmTextView.setText("주의사항: " + pill.getAtpnQesitm());
        seQesitmTextView.setText("부작용: " + pill.getSeQesitm());
        etcotcTextView.setText("분류: " + pill.getEtcotc());

        // Picasso를 사용하여 약물 이미지 로딩
        Picasso.get().load(pill.getItemImage()).into(itemImageView);

        // "복용 중인 약 추가" 버튼 클릭 리스너 설정
        addMedicationButton.setOnClickListener(v -> addMedicationToDatabase());
    }

    // 약물을 서버에 추가하는 메서드
    private void addMedicationToDatabase() {
        // 기기의 고유 ID를 가져옴
        String userId = DeviceUtil.getDeviceId(this);
        Log.d("PillDetailActivity", "기기 ID: " + userId);

        // 서버로 보낼 JSON 객체 생성
        JsonObject pillJson = new JsonObject();
        pillJson.addProperty("user_id", userId);
        pillJson.addProperty("itemSeq", pill.getItemSeq());
        pillJson.addProperty("itemName", pill.getItemName());
        pillJson.addProperty("efcyQesitm", pill.getEfcyQesitm());
        pillJson.addProperty("atpnQesitm", pill.getAtpnQesitm());
        pillJson.addProperty("seQesitm", pill.getSeQesitm());
        pillJson.addProperty("etcotc", pill.getEtcotc());
        pillJson.addProperty("itemImage", pill.getItemImage());

        // ApiService를 사용하여 API 호출을 준비
        ApiService apiService = ApiClient.getApiService();
        Call<ResponseBody> call = apiService.addPill(pillJson);

        // API 호출을 비동기적으로 실행
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
