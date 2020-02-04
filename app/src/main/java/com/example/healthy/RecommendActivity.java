package com.example.healthy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {

    ArrayList<Item_RV> listItem;
    int water_standar=4;
    int sleep_standar=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Init_ListDrink();
        Init_ListFood();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lời khuyên cho bạn ");
        actionBar.setDisplayHomeAsUpEnabled(true);

        int[] x = {1,2,3,4};
        int[] y = {0,1,0,1};

        LinearRegression linearRegression = new LinearRegression(x,y);

        ArrayList<String> list = new ArrayList<String>();

        Intent intent = getIntent();

        ArrayList<Integer> listFood = new ArrayList<Integer>();
        listFood = intent.getIntegerArrayListExtra("listFood");

        for(int i=0;i<listFood.size();i++)
        {
            if(listFood.get(i)!=0)
                list.add(listFood.get(i)+"\t\t"+listItem.get(i).getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);

        ListView listView = (ListView) findViewById(R.id.lvRec);
        listView.setAdapter(adapter);

        TextView txWater = (TextView) findViewById(R.id.recWater);
        TextView txSleep = (TextView) findViewById(R.id.sleepNight);
        txWater.setText(water_standar+linearRegression.predict(6)+"");
        txSleep.setText(sleep_standar-linearRegression.predict(5)+"");







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

    private void Init_ListDrink() {
        listItem = new ArrayList<Item_RV>();
        listItem.add(new Item_RV(R.drawable.ruou, "Rượu", 0,"0:00",1));
        listItem.add(new Item_RV(R.drawable.bia, "Bia", 0,"0:00",2));
        listItem.add(new Item_RV(R.drawable.trasua, "Trà Sữa", 0,"0:00",3));
        listItem.add(new Item_RV(R.drawable.nuocloc, "Nước Lọc", 0,"0:00",4));
        listItem.add(new Item_RV(R.drawable.coca, "Nước Ngọt", 0,"0:00",5));
        listItem.add(new Item_RV(R.drawable.soda, "Nước có Ga", 0,"0:00",6));
        listItem.add(new Item_RV(R.drawable.ruou, "Rượu thuốc", 0,"0:00",7));
        listItem.add(new Item_RV(R.drawable.bia, "Bia", 0,"0:00",8));
        listItem.add(new Item_RV(R.drawable.trasua, "Strongbow", 0,"0:00",9));
        listItem.add(new Item_RV(R.drawable.nuocloc, "Nước chanh", 0,"0:00",10));
        listItem.add(new Item_RV(R.drawable.coca, "Soda", 0,"0:00",11));
        listItem.add(new Item_RV(R.drawable.soda, "Nước cam", 0,"0:00",12));
    }

    private void Init_ListFood() {
        listItem.add(new Item_RV(R.drawable.rices, "Cơm", 0,"0:00",13));
        listItem.add(new Item_RV(R.drawable.pho, "Phở", 0,"0:00",14));
        listItem.add(new Item_RV(R.drawable.cake, "Bánh Ngọt", 0,"0:00",15));
        listItem.add(new Item_RV(R.drawable.fastfood, "Sandwich", 0,"0:00",16));
        listItem.add(new Item_RV(R.drawable.salad, "Salad", 0,"0:00",17));
        listItem.add(new Item_RV(R.drawable.seafood, "Hải sản", 0,"0:00",18));
        listItem.add(new Item_RV(R.drawable.rice, "Cháo", 0,"0:00",19));
        listItem.add(new Item_RV(R.drawable.pho, "Hủ tiếu", 0,"0:00",20));
        listItem.add(new Item_RV(R.drawable.cake, "Bánh Plan", 0,"0:00",21));
        listItem.add(new Item_RV(R.drawable.fastfood, "Berger", 0,"0:00",22));
        listItem.add(new Item_RV(R.drawable.salad, "Rau xanh", 0,"0:00",23));
        listItem.add(new Item_RV(R.drawable.seafood, "Cua ghệ", 0,"0:00",24));
    }



}
