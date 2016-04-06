package com.bluelife.test.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HiWin10 on 2016/4/6.
 */
public interface LoginApi {
    @FormUrlEncoded
    @POST("logging.php?action=login&loginsubmit=yes&inajax=1")
    Call<ResponseBody> login(@Field("loginfield") String type, @Field("username") String name, @Field("password") String pwd);
}
