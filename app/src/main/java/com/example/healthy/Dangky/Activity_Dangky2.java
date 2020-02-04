package com.example.healthy.Dangky;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthy.MainScreenActivity;
import com.example.healthy.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Dangky2 extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dangky2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Back");

        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("Data");



        TextView ten = (TextView) findViewById(R.id.name);
        TextView tuoi = (TextView) findViewById(R.id.age);
        TextView cao = (TextView) findViewById(R.id.height);
        TextView nang = (TextView) findViewById(R.id.weight);
        TextView gioitinh = (TextView) findViewById(R.id.gender);

        ten.setText(list.get(0));
        tuoi.setText((2020-Integer.parseInt(list.get(1)))+"");
        cao.setText(list.get(3));
        nang.setText(list.get(4));
        gioitinh.setText(list.get(2));


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
