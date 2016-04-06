package com.bluelife.test.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Header;

public class MainActivity extends AppCompatActivity implements Callback<ResponseBody> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CookieJar cookieJar = new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        for (int i = 0; i < cookies.size(); i++) {
                            Cookie cookie=cookies.get(i);
                            //Log.w("sss",cookie.name()+"=="+cookie.value());
                        }
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);

                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://www.hi-pda.com/forum/")
                .client(okHttpClient).build();
        LoginApi loginApi=retrofit.create(LoginApi.class);
        Call<ResponseBody> call=loginApi.login("username","bluelife","kkkfffddd");
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            Log.w("ssse",e);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

        Log.w("ssss",t);
    }
}
