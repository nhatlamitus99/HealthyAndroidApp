package com.example.healthy.Dangky;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.healthy.MainActivity;
import com.example.healthy.MainScreenActivity;
import com.example.healthy.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Dangky1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dangky1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Back");









    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                SendData();
                GetData();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SendData() {
        Intent intent = new Intent(Activity_Dangky1.this, MainActivity.class);
        intent.putStringArrayListExtra("Data", GetData());
        startActivity(intent);
    }

    private ArrayList<String> GetData() {
        EditText name = (EditText) findViewById(R.id.name);
        DatePicker date = (DatePicker) findViewById(R.id.date);
        RadioButton nam = (RadioButton) findViewById(R.id.nam);
        RadioButton nu = (RadioButton) findViewById(R.id.nu);
        EditText height = (EditText) findViewById(R.id.height);
        EditText weight = (EditText) findViewById(R.id.weight);

        ArrayList<String> list = new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 9, 9);

        list.add(name.getText().toString());
        list.add(date.getYear()+"");
        if(nam.isChecked())
            list.add("Nam");
        else
            list.add("Nữ");
        list.add(height.getText().toString());
        list.add(weight.getText().toString());

        return list;

    }

}



