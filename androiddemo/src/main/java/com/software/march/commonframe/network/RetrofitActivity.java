package com.software.march.commonframe.network;

import android.os.Bundle;
import android.util.Log;

import com.software.march.BuildConfig;
import com.software.march.R;
import com.software.march.appcommonlibrary.BaseActivity;
import com.software.march.commonframe.network.api.ApiClass;
import com.software.march.commonframe.network.bean.BookBean;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2017/3/23
 */
public class RetrofitActivity extends BaseActivity {

    // http://blog.csdn.net/u012301841/article/details/49685677

    private final String TAG = getClass().getSimpleName();
    private ApiClass apiClass;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        // osVersion=6.0.1&deviceId=&appVersion=7.8.6&token=566a3fa581e6e3b434f44a75&os=Android]

        // The Retrofit class generates an implementation of the GitHubService interface.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClass.DOU_BAN_BASE_URL)
                // 增加返回值为 String 的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                // 增加返回值为 Gson 的支持( 以实体类返回 )
                .addConverterFactory(GsonConverterFactory.create())
                // 使用自己创建的OkHttp
                .client(getOkHttpClient())
                .build();
        apiClass = retrofit.create(ApiClass.class);
        book1();
        book2();
    }

    private OkHttpClient getOkHttpClient() {
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);

        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // OkHttp进行添加拦截器loggingInterceptor
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }

    private void book1() {
        // Each Call from the created ApiClass can make a synchronous or asynchronous HTTP request to the remote WebServer.
        Call<String> call = apiClass.book1("1220562");
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, "book1:" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e(TAG, "book1:" + throwable.getMessage());
            }
        });
    }

    private void book2() {
        Call<BookBean> call = apiClass.book2("1220562");
        call.enqueue(new Callback<BookBean>() {

            @Override
            public void onResponse(Call<BookBean> call, Response<BookBean> response) {
                Log.i(TAG, "book2:" + response.body().toString());
            }

            @Override
            public void onFailure(Call<BookBean> call, Throwable throwable) {
                Log.e(TAG, "book2:" + throwable.getMessage());
            }
        });
    }
}