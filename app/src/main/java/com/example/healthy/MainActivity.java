package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthy.Dangky.Activity_Dangky1;
import com.example.healthy.Dangky.Activity_Dangky2;
import com.example.healthy.Dangky.Item;

import java.io.BufferedReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int label;
    ImageView imgbtnFeel;
    ArrayList<String> listData = new ArrayList<String>();
    MyDataBase dbb=new MyDataBase(this);
    ArrayList<Integer> listFeel = new ArrayList<Integer>();
    ArrayList<Integer> listWater = new ArrayList<Integer>();
    ArrayList<Integer> listSleep = new ArrayList<Integer>();
    ArrayList<Integer> listFood = new ArrayList<Integer>();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.activity_main_screen);

        ActionBar actionBar = getSupportActionBar();
        String time = ProcessDate();
        actionBar.setTitle(time);

        try {
            listFood = dbb.RecommendFood();
            dbb.datatest();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        function();

        readNews();

        doExcercise();

        String test = crawlData();


        imgbtnFeel = (ImageView) findViewById(R.id.feel);
        registerForContextMenu(imgbtnFeel);




    }

    private void function() {
        ImageButton imgbtnContact = (ImageButton) findViewById(R.id.phone);
        ImageButton imgbtnFood = (ImageButton) findViewById(R.id.food);
        ImageButton imgbtnExercise = (ImageButton) findViewById(R.id.exercise);
        ImageButton imgbtnSleep = (ImageButton) findViewById(R.id.sleep);
        ImageButton imgbtnWater = (ImageButton) findViewById(R.id.water);
        ImageButton imgbtnAlarm = (ImageButton) findViewById(R.id.alarm);
        ImageButton imgbtnSta = (ImageButton) findViewById(R.id.statistic);
        ImageButton imgbtnRec = (ImageButton) findViewById(R.id.idea);
        ImageButton imgbtnSet = (ImageButton) findViewById(R.id.setting);
        final ImageButton imgbtnUser = (ImageButton) findViewById(R.id.user);

        imgbtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });


        imgbtnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivityForResult(intent,1);
            }
        });

        imgbtnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitiesActivity.class);
                startActivityForResult(intent,2);
            }
        });

        imgbtnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SleepActivity.class);
                startActivity(intent);
            }
        });

        imgbtnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterActivity.class);
                startActivity(intent);
            }
        });

        imgbtnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

        imgbtnSta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
                intent.putIntegerArrayListExtra("label", listFeel);
                startActivity(intent);
            }
        });


        imgbtnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Dangky1.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        listData = intent.getStringArrayListExtra("Data");
        listWater = intent.getIntegerArrayListExtra("listWater");
        listSleep = intent.getIntegerArrayListExtra("listSleep");


        imgbtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Dangky2.class);
                intent.putStringArrayListExtra("Data", listData);
                startActivity(intent);
            }
        });

        imgbtnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecommendActivity.class);
                intent.putIntegerArrayListExtra("Water", listWater);
                intent.putIntegerArrayListExtra("Sleep", listSleep);
                intent.putIntegerArrayListExtra("listFood", listFood);
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

    private void doExcercise() {

        ImageView do1 = (ImageView) findViewById(R.id.do1);
        ImageView do2 = (ImageView) findViewById(R.id.do2);
        ImageView do3 = (ImageView) findViewById(R.id.do3);
        ImageView do4 = (ImageView) findViewById(R.id.do4);
        ImageView do5 = (ImageView) findViewById(R.id.do5);
        ImageView do6 = (ImageView) findViewById(R.id.do6);

        do1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://genvita.vn/bai-bao/bai-tap-the-duc-buoi-sang-giup-ban-tinh-tao-tran-day-nang-luong-suot-ngay-dai";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        do2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.khoedep.vn/bai-tap-the-duc-buoi-sang-giam-can-mo-bung/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        do3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ngocdung.net/bai-tap-the-duc-don-gian-giam-mo-cap-toc-chi-sau-7-ngay";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        do4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://thietkebeboikinhdoanh.com/cac-bai-tap-the-duc-giam-mo-bung/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        do5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tricottan.com/bai-tap-thoat-vi-dia-dem/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        do6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://dinhphapvuong.com/bai-tap-the-duc-tang-cuong-sinh-ly-nam/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }


    private String crawlData() {

        StringBuffer data = new StringBuffer();

        try {
            URL url = new URL("https://vietnamnet.vn/vn/suc-khoe/");
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


        return data.toString();

    }



    private String ProcessDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        return  currentDate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.light:
                settingLight();

                break;
            case R.id.dark:
                settingDark();

                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void settingDark() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void settingLight() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Bạn cảm thấy thế nào ?");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.a:
                label=7;
                break;
            case R.id.b:
                label=6;
                break;
            case R.id.c:
                label=5;
                break;
            case R.id.d:
                label=4;
                break;
            case R.id.e:
                label=3;
                break;
            case R.id.f:
                label=2;
                break;
            case R.id.g:
                label=1;
        }

        dbb.insertState(ProcessDate(),label);
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        listFeel.add(label);
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1)
        {
            ArrayList<Integer> l=data.getIntegerArrayListExtra("data");
            ArrayList<Integer> q=data.getIntegerArrayListExtra("quality");
            ArrayList<String> t=data.getStringArrayListExtra("time");
            for (int i=0;i<l.size();i++)
            {
                Log.d("************",l.get(i)+"#"+q.get(i)+"#"+ProcessDate());
                dbb.insertFood(ProcessDate(),l.get(i),q.get(i));
                //dbb.insertFood(date,);
            }
        }
    }
}
