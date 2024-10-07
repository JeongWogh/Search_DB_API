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

    @POST("api/pills/add")
    Call<ResponseBody> addPill(@Body Pill pill);

    // 특정 증상에 맞는 약 검색
    @GET("api/pills/search")
    Call<List<Pill>> searchPills(@Query("symptom") String symptom);

    // 특정 약 정보 가져오기
    @GET("api/pills/{itemSeq}")
    Call<Pill> getPillById(@Path("itemSeq") int itemSeq);

    // 만약 서버가 단일 객체를 반환한다면
    @GET("/api/pills/search")
    Call<Pill> searchPill(  // 단일 객체로 변경
                            @Query("symptom") String symptom,
                            @Query("selectedSymptoms") List<String> selectedSymptoms
    );
}