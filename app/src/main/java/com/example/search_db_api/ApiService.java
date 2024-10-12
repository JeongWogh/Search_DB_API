package com.example.search_db_api;

import com.google.gson.JsonArray;
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

    // 전체 약 정보 가져오기
    @GET("/api/pills/search")
    Call<List<Pill>> searchPills(
            @Query("symptom") String symptom,  // 텍스트 검색어
            @Query("selectedSymptoms") List<String> selectedSymptoms  // 체크박스에서 선택한 증상들
    );

    // 약물 정보를 추가하는 POST 요청
    @POST("api/pills/add")
    Call<ResponseBody> addPill(@Body Pill pill);  // 요청 본문에 Pill 객체를 담아 서버로 전송

    // 특정 증상에 맞는 약 검색 (단일 텍스트 검색어를 기반으로 검색)
    @GET("api/pills/search")
    Call<List<Pill>> searchPills(@Query("symptom") String symptom);

    // 특정 약의 상세 정보를 itemSeq로 가져오기
    @GET("api/pills/{itemSeq}")
    Call<Pill> getPillById(@Path("itemSeq") int itemSeq);  // 경로 매개변수로 itemSeq 사용

    // 서버가 단일 객체를 반환하는 경우를 위한 API
    @GET("/api/pills/search")
    Call<Pill> searchPill(  // 단일 객체로 변경
                            @Query("symptom") String symptom,
                            @Query("selectedSymptoms") List<String> selectedSymptoms
    );
}
