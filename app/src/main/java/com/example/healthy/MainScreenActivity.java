package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.healthy.Dangky.Activity_Dangky1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainScreenActivity extends AppCompatActivity {

    ImageView imgbtnFeel;
    int label;

    MyDataBase db=new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main_screen);

        String str = crawlData();
        Toast.makeText(this, str+"A", Toast.LENGTH_LONG).show();


        ActionBar actionBar = getSupportActionBar();
        String time = ProcessDate();
        actionBar.setTitle(time);

        function();

        imgbtnFeel = (ImageView) findViewById(R.id.feel);
        registerForContextMenu(imgbtnFeel);


        readNews();







    }

    private String crawlData() {

        StringBuffer data = new StringBuffer();

        try {
            URL url = new URL("https://www.fit.hcmus.edu.vn/vn/feed.aspx");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    data.append(inputLine);
                }
            } catch (Exception ex) {

            } finally {
                con.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


         return data.toString().substring(0,10);

    }

    private void function() {
        ImageButton imgbtnFood = (ImageButton) findViewById(R.id.food);
        ImageButton imgbtnExercise = (ImageButton) findViewById(R.id.exercise);
        ImageButton imgbtnSleep = (ImageButton) findViewById(R.id.sleep);
        ImageButton imgbtnWater = (ImageButton) findViewById(R.id.water);
        ImageButton imgbtnAlarm = (ImageButton) findViewById(R.id.alarm);
        ImageButton imgbtnSta = (ImageButton) findViewById(R.id.statistic);
        ImageButton imgbtnRec = (ImageButton) findViewById(R.id.idea);
        ImageButton imgbtnSet = (ImageButton) findViewById(R.id.setting);


        imgbtnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, FoodActivity.class);
                startActivityForResult(intent,1);
            }
        });

        imgbtnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });

        imgbtnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, SleepActivity.class);
                startActivity(intent);
            }
        });

        imgbtnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, WaterActivity.class);
                startActivity(intent);
            }
        });

        imgbtnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

        imgbtnSta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, StatisticActivity.class);
                startActivity(intent);
            }
        });

        imgbtnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, RecommendActivity.class);
                startActivity(intent);
            }
        });

        imgbtnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, Activity_Dangky1.class);
                startActivity(intent);
            }
        });
    }

    private void readNews() {

        ImageView news1 = (ImageView) findViewById(R.id.n1);
        ImageView news2 = (ImageView) findViewById(R.id.n2);
        ImageView news3 = (ImageView) findViewById(R.id.n3);
        ImageView news4 = (ImageView) findViewById(R.id.n4);
        ImageView news5 = (ImageView) findViewById(R.id.n5);
        ImageView news6 = (ImageView) findViewById(R.id.n6);

        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/hai-phong-cho-xuat-vien-3-benh-nhan-nghi-ngo-ncov-co-ket-qua-am-tinh-n168482.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/niem-vui-ngay-xuat-vien-cua-benh-nhan-nhiem-ncov-tai-bv-cho-ray-n168480.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        news3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/cdc-quang-ninh-du-dieu-kien-va-nang-luc-thuc-hien-xet-nghiem-ban-dau-ncov-n168476.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        news4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/hai-phong-cho-xuat-vien-3-benh-nhan-nghi-ngo-ncov-co-kem-tit-qua-anh-n168482.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        news5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/cach-phong-ngua-vi-rut-corona-tan-cong-nguoi-gia--n168366.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        news6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://suckhoedoisong.vn/hai-phong-cho-xuat-vien-3-benh-nhan-nghi-ngo-ncov-co-ket-qua-am-tinh-n168482.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

    private String ProcessDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        return  currentDate;
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Bạn cảm thấy thế nào?");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.a:
                label=1;
            case R.id.b:
                label=2;
            case R.id.c:
                label=3;
            case R.id.d:
               label=4;
            case R.id.e:
               label=5;
            case R.id.f:
                label=6;
            case R.id.g:
                label=7;
        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("***********","3");
    }

}
