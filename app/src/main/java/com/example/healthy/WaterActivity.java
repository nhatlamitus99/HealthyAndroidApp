package com.example.healthy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.healthy.Dangky.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Locale;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class WaterActivity extends AppCompatActivity {

    ArrayList<Item_RV> list;
    Adapter_RV adapter_rv;

    ArrayList<Item> list_water = new ArrayList<Item>();
    ArrayList<Integer> listWater = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        ActionBar actionBar = getSupportActionBar();
        String time = ProcessDate();
        actionBar.setTitle(time);
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);


        Init_ListFood();


        adapter_rv = new Adapter_RV(list, this);
        rv.setAdapter(new ScaleInAnimationAdapter(adapter_rv));
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                GetData();
                Intent intent = new Intent(WaterActivity.this, MainActivity.class);
                intent.putIntegerArrayListExtra("listWater", listWater);
                startActivity(intent);
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void GetData() {
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getNumber()!=0)
            {
                list_water.add(new Item(list.get(i).getTitle(), list.get(i).getNumber()));
                listWater.add(list.get(i).getNumber());
            }
            Toast.makeText(this, list_water.size()+" hoạt động được cập nhật !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private String ProcessDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        return  currentDate;
    }




    private void Init_ListFood() {
        list = new ArrayList<Item_RV>();
        list.add(new Item_RV(R.drawable.sun, "Buổi Sáng", 0));
        list.add(new Item_RV(R.drawable.sun, "Buổi Trưa", 0));
        list.add(new Item_RV(R.drawable.night, "Buổi Tối", 0));
    }
}
