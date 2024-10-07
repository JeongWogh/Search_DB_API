package com.example.search_db_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//     private static final String BASE_URL = "http://10.0.2.2:5000/";  // 가상 머신 쓸 떄 Flask 서버 주소
    private static final String BASE_URL = "http://10.100.1.110:5000/"; // 폰 쓸 때 서버 주소
//    private static final String BASE_URL = "http://192.168.1.9:5000/"; // 집에서 할 때
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getClient().create(ApiService.class);
    }
}
