package com.example.healthy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhắc Nhở");
        actionBar.setDisplayHomeAsUpEnabled(true);


        CheckBox checkBox1 = (CheckBox) findViewById(R.id.meal1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.meal2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.meal3);

        RadioButton radioButton1 = (RadioButton) findViewById(R.id.alarm1);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.alarm2);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.alarm3);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.alarm4);

        RadioButton radioSleep1 = (RadioButton) findViewById(R.id.sleep1);
        RadioButton radioSleep2 = (RadioButton) findViewById(R.id.sleep2);
        RadioButton radioWakeup1 = (RadioButton) findViewById(R.id.wake1);
        RadioButton radioWakeup2 = (RadioButton) findViewById(R.id.wake2);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);

        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        radioButton4.setOnClickListener(this);

        radioSleep1.setOnClickListener(this);
        radioSleep2.setOnClickListener(this);
        radioWakeup1.setOnClickListener(this);
        radioWakeup2.setOnClickListener(this);





    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.meal1:
                Toast.makeText(this, "Đặt báo thức nhắc nhở ăn điểm tâm ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.meal2:
                Toast.makeText(this, "Đặt báo thức nhắc nhở ăn trưa ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.meal3:
                Toast.makeText(this, "Đặt báo thức nhắc nhở ăn tối ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm1:
                Toast.makeText(this, "Đặt báo thức nhắc nhở \n uống nước sau mỗi 1h ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm2:
                Toast.makeText(this, "Đặt báo thức nhắc nhở \n uống nước sau mỗi 2h ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm3:
                Toast.makeText(this, "Đặt báo thức nhắc nhở \n uống nước sau mỗi 3h ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarm4:
                Toast.makeText(this, "Đặt báo thức nhắc nhở \n uống nước sau mỗi 4h ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wake1:
                Toast.makeText(this, "Đặt báo thức thức dậy buổi sáng ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wake2:
                Toast.makeText(this, "Đặt báo thức thức dậy buổi trưa ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sleep1:
                Toast.makeText(this, "Đặt báo thức nhắc nhở ngủ ban ngày ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sleep2:
                Toast.makeText(this, "Đặt báo thức nhắc nhở ngủ ban đêm ", Toast.LENGTH_SHORT).show();

        }
        Intent intent = new Intent(AlarmActivity.this, Alarm.class);
        startActivity(intent);
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
