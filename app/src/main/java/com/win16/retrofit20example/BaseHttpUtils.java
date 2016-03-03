package com.win16.retrofit20example;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @desc A tool based on http, using Okhttp and Retrofit to get/post request.
 *
 * @date 2016/2/18 19:24
 * @copyright TCL-MIG
 * @author REXZOU
 */
public class BaseHttpUtils {

    public static final String TAG = "BaseHttpUtils";

    public static final int CACHE_SIZE = 4*1024*1024; //cache size
    public static final int NETWORK_TIME_OUT = 20; //network time out

    private static OkHttpClient sDefaultHttpClient;

    private Context mContext;

    private Retrofit mRetrofit;

    private OkHttpClient mOKHttpClient;

    private String mServerUrl; //server url

    private boolean mEnableLog;

    private HttpLoggingInterceptor.Level mLogLevel;

    public BaseHttpUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    public BaseHttpUtils(Context context, String serverUrl) {
        mContext = context.getApplicationContext();
        mServerUrl = serverUrl;
    }

    public BaseHttpUtils(Context context, String serverUrl, OkHttpClient okHttpClient) {
        mContext = context.getApplicationContext();
        mServerUrl = serverUrl;
        mOKHttpClient = okHttpClient;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (BaseHttpUtils.class) {
                if( mRetrofit == null) {
                    mRetrofit = initDefault();
                }
            }
        }
        return mRetrofit;
    }

    /**
     * Set your own component, normally you don't need to set intently unless default function can not reach your requirement.
     * @param retrofit
     */
    public void setRetrofit(Retrofit retrofit) {
        if (retrofit != null) {
            mRetrofit = retrofit;
            mServerUrl = mRetrofit.baseUrl().toString();
        }
    }

    /**
     * 设置日志级别
     * @param enable
     * @param logLevel
     */
    public void setLogLevel(boolean enable, HttpLoggingInterceptor.Level logLevel) {
        mEnableLog = enable;
        mLogLevel = logLevel;
    }

    private Retrofit initDefault() {
        Retrofit.Builder builder = new Retrofit.Builder();
        if( mOKHttpClient == null) {
            OkHttpClient.Builder okBuilder = buildDefalutClient(mContext);
            if( mEnableLog) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(mLogLevel);
                okBuilder.addInterceptor(interceptor);
                mOKHttpClient = okBuilder.build();
            }
            else {
                mOKHttpClient = getDefaultOkHttpClient(okBuilder);
            }

        }
        builder.client(mOKHttpClient);
        builder.addConverterFactory(new StringConverterFactory());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.baseUrl(mServerUrl);
        return builder.build();
    }

    /**
     * Generate a default OKHttpClient with default parameter.
     * If you have special requirements, please create by yourself.
     * @return
     */
    public static synchronized OkHttpClient getDefaultOkHttpClient(OkHttpClient.Builder builder ) {
        if( sDefaultHttpClient == null) {
//        builder.addInterceptor()
            sDefaultHttpClient = builder.build();
        }
        return sDefaultHttpClient;
    }

    private static OkHttpClient.Builder buildDefalutClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(context.getCacheDir(), CACHE_SIZE));
        builder.addInterceptor(new DefaultCacheInterceptor(context));
        builder.connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        return builder;
    }
}
