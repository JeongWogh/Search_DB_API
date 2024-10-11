package com.example.search_db_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // 서버의 기본 URL 설정
    // Android Emulator(가상 머신)에서 로컬 호스트에 접근하기 위한 주소
    // 실제 사용 시 환경에 따라 URL을 바꾸어야 함
    // private static final String BASE_URL = "http://10.0.2.2:5000/";

    // 실제 디바이스를 사용할 때 서버 주소
    // 현재 연결된 Wi-Fi 네트워크에서 서버의 IP 주소를 입력해야 함
    // 서버의 IP 주소는 상황에 따라 달라지므로 수시로 변경해 사용
    private static final String BASE_URL = "http://10.100.1.99:5000/";

    // 집에서 서버에 접근할 때 사용하는 주소
    // private static final String BASE_URL = "http://192.168.1.9:5000/";

    // Retrofit 객체를 저장할 변수
    private static Retrofit retrofit;

    // Retrofit 객체를 생성하여 반환하는 메서드
    public static Retrofit getClient() {
        // retrofit 객체가 아직 생성되지 않았을 경우 새로 생성
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // 서버의 기본 URL 설정
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 데이터를 변환하기 위해 Gson을 사용
                    .build(); // Retrofit 객체 빌드 및 생성
        }
        return retrofit; // 생성된 retrofit 객체 반환
    }

    // ApiService 인터페이스의 구현체를 반환하는 메서드
    public static ApiService getApiService() {
        // getClient() 메서드를 호출하여 ApiService 객체를 생성 및 반환
        return getClient().create(ApiService.class);
    }
}
