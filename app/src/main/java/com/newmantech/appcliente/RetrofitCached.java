package com.newmantech.appcliente;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCached {

    public static Boolean hasNetwork(Context context) {
        Boolean isConnected = false; // Initial Value
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
            isConnected = true;
        return isConnected;
    }

    public static OkHttpClient getCacheEnabledRetrofit(final Context context) {
        Log.i("RetrofitCached", "body: getCacheEnabledRetrofit");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
                .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1024))
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.i("RetrofitCached", "body: session " + hasNetwork(context));
                        if(hasNetwork(context)){
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                            //Request.Builder builder = request.newBuilder();
                            request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
                            request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
                        }
                        else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                            request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
        //okHttpClient.newBuilder().addInterceptor(interceptor).build();
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(;

        /*Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(RetrofitAPI.BASE_URL)
                .build();*/

        return okHttpClient;
    }


}