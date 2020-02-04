package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.healthy.Dangky.Activity_Dangky1;
import com.example.healthy.Dangky.Activity_Dangky2;
import com.example.healthy.Dangky.Item;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView imgbtnFeel;
    int label;
    ArrayList<String> listData = new ArrayList<String>();
    MyDataBase dbb=new MyDataBase(this);
    ArrayList<Integer> listFeel = new ArrayList<Integer>();
    ArrayList<Integer> listWater = new ArrayList<Integer>();
    ArrayList<Integer> listSleep = new ArrayList<Integer>();
    ArrayList<Integer> listFood = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        ImageButton imgbtnFood = (ImageButton) findViewById(R.id.food);
        ImageButton imgbtnExercise = (ImageButton) findViewById(R.id.exercise);
        ImageButton imgbtnSleep = (ImageButton) findViewById(R.id.sleep);
        ImageButton imgbtnWater = (ImageButton) findViewById(R.id.water);
        ImageButton imgbtnAlarm = (ImageButton) findViewById(R.id.alarm);
        ImageButton imgbtnSta = (ImageButton) findViewById(R.id.statistic);
        ImageButton imgbtnRec = (ImageButton) findViewById(R.id.idea);
        ImageButton imgbtnSet = (ImageButton) findViewById(R.id.setting);
        final ImageButton imgbtnUser = (ImageButton) findViewById(R.id.user);

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





        imgbtnFeel = (ImageView) findViewById(R.id.feel);
        registerForContextMenu(imgbtnFeel);

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
