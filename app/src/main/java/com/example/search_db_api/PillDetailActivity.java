package com.example.search_db_api;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PillDetailActivity extends AppCompatActivity {

    private Pill pill;
    private static final String DB_URL = "jdbc:mariadb://localhost:3308/pill";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_details);

        // 데이터베이스 테이블 생성
        createTable();

        // 인텐트에서 Pill 객체 가져오기
        pill = getIntent().getParcelableExtra("selectedPill");

        if (pill != null) {
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

            // 이미지 로딩
            Picasso.get().load(pill.getItemImage()).into(itemImageView);

            // 복용 중인 약 추가 버튼 클릭 리스너 설정
            addMedicationButton.setOnClickListener(v -> addMedicationToDatabase());
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user_medications (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT NOT NULL," +
                "item_name VARCHAR(255) NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this,"테이블 생성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addMedicationToDatabase() {
        String sql = "INSERT INTO user_medications (user_id, item_name) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, getUserId());
            pstmt.setString(2, pill.getItemName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                Toast.makeText(this, "복용 중인 약이 추가되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "약 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "데이터베이스 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private int getUserId() {
        // TODO: 실제 사용자 ID를 반환하는 로직 구현
        return 1; // 임시로 1 반환
    }
}