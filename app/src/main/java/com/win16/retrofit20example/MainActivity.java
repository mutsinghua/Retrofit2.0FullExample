package com.win16.retrofit20example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.win16.retrofit20example.protocol.appcenter.AppListApi;
import com.win16.retrofit20example.protocol.appcenter.pojo.Screen1Pojo;
import com.win16.retrofit20example.protocol.wallpaper.WallPaperApi;
import com.win16.retrofit20example.protocol.wallpaper.pojo.WallPaperPojo;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final BaseHttpUtils baseHttpUtils = new BaseHttpUtils(this, "http://www.baidu.com/test-api/api/");
        //设置日志输出
        baseHttpUtils.setLogLevel(true, HttpLoggingInterceptor.Level.BODY);

        //异步调用
        AppListApi appListApi = baseHttpUtils.getRetrofit().create(AppListApi.class);
        Call<Screen1Pojo> call = appListApi.getAppList(1, 20);
        call.enqueue(new Callback<Screen1Pojo>() {
            @Override
            public void onResponse(Call<Screen1Pojo> call, Response<Screen1Pojo> response) {
                Log.d("Main2Activity", "response.body()");

            }

            @Override
            public void onFailure(Call<Screen1Pojo> call, Throwable t) {
                Log.e("Main2Activity", t.getMessage());
            }
        });

        //异步调用
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                WallPaperApi service = baseHttpUtils.getRetrofit().create(WallPaperApi.class);
                Call<WallPaperPojo> wallPaperPojoCall = service.getWallPaper("1",10,10);
                try {
                    Response<WallPaperPojo> res = wallPaperPojoCall.execute();
                    if( res.isSuccess() && res.body() != null) {
                        WallPaperPojo pojo = res.body();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        //上传文件示例
        appListApi.sendFile(RequestBody.create(MediaType.parse("text/html"), new File("/sdcard/test.html")));

        //上传多个文件示例
        appListApi.sendFile(RequestBody.create(MediaType.parse("text/html"), new File("/sdcard/test.html")),RequestBody.create(MediaType.parse("text/html"), new File("/sdcard/test.html")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
