package com.example.search_db_api;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // 약물 검색: 사용자가 입력한 증상과 선택한 증상들을 기반으로 약물 리스트를 가져옵니다.
    @GET("/api/pills/search")
    Call<List<Pill>> searchPills(
            @Query("symptom") String symptom,  // 사용자가 입력한 텍스트 검색어
            @Query("selectedSymptoms") List<String> selectedSymptoms  // 체크박스에서 선택된 증상 목록
    );

    // 약물 추가: 클라이언트에서 서버로 약물 정보를 전송하여 서버에 저장합니다.
    @POST("/api/pills/add")
    Call<ResponseBody> addPill(@Body JsonObject pillJson);  // JSON 객체(pillJson)를 서버로 전송

    // 단일 텍스트 검색어를 기반으로 약물 리스트를 가져옵니다.
    @GET("api/pills/search")
    Call<List<Pill>> searchPills(@Query("symptom") String symptom);

    // 특정 약물의 상세 정보를 itemSeq를 통해 가져옵니다.
    @GET("api/pills/{itemSeq}")
    Call<Pill> getPillById(@Path("itemSeq") int itemSeq);  // itemSeq는 약물의 고유 ID입니다.

    // 단일 Pill 객체를 반환하는 검색: 사용자가 입력한 증상과 선택한 증상들을 기반으로 단일 약물 정보를 가져옵니다.
    @GET("/api/pills/search")
    Call<Pill> searchPill(
            @Query("symptom") String symptom,  // 사용자가 입력한 텍스트 검색어
            @Query("selectedSymptoms") List<String> selectedSymptoms  // 체크박스에서 선택된 증상 목록
    );
}
