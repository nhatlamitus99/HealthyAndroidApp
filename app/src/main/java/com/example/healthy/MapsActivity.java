package com.example.healthy;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Hospital> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ListView lv = (ListView) findViewById(R.id.lv);
        initList();

        HospitalAdapter adapter = new HospitalAdapter(this, list, R.layout.locations);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LatLng pos = new LatLng(list.get(position).getLatitude(), list.get(position).getLongitude());
                mMap.addMarker(new MarkerOptions().position(pos).title(list.get(position).getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 13));
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+list.get(position).getPhone().substring(12)));
                startActivity(intent);
                return false;
            }
        });
    }

    private void initList() {
        list = new ArrayList<Hospital>();
        list.add(new Hospital("BỆNH VIỆN AN BÌNH", "Điện thoại: (028)9234260", "Địa chỉ: 146 AN BÌNH, P.7, Q.5, TP. HCM", 10.7542491,106.6693767));
        list.add(new Hospital("BỆNH VIỆN ĐA KHOA TƯ NHÂN AN SINH", "Điện thoại: (028)8457777", "Địa chỉ: 10 TRẦN HUY LIỆU, P.12, Q.PN, TP. HCM", 10.7911387,106.6764335));
        list.add(new Hospital("BỆNH VIỆN QUÂN DÂN MIỀN ĐÔNG", "Điện thoại: (028)7307125", "Địa chỉ: 50 LÊ VĂN VIỆT, P.HIỆP PHÚ, Q.9, TP.HCM", 10.8472829,106.7741082));
        list.add(new Hospital("BỆNH VIỆN UNG BƯỚU TP.HCM", "Điện thoại: (028)9234260", "Địa chỉ: 146 AN BÌNH, P.7, Q.5, TP. HCM", 10.7542491,106.6693767));
        list.add(new Hospital("BỆNH VIỆN TRUYỀN MÁU HUYẾT HỌC", "Điện thoại: (028)8397535", "Địa chỉ: 201 PHẠM VIẾT CHÁNH, P.NCT, Q.1, TP. HCM", 10.7690923,106.6820663));
        list.add(new Hospital("BỆNH VIỆN Y HỌC CỔ TRUYỀN", "Điện thoại: (028)9326579", "Địa chỉ: 179 NAM KỲ KHỞI NGHĨA, P.7, Q.3, TP. HCM", 10.7858519,106.6854348));
        list.add(new Hospital("BỆNH VIỆN TAI MŨI HỌNG TP.HCM", "Điện thoại: (028)9317381", "Địa chỉ: 155B TRẦN QUỐC THẢO, P.9, Q.3, TP. HCM", 10.7843575,106.6818327));
        list.add(new Hospital("BỆNH VIỆN CHỢ RẪY", "Điện thoại: (028)8554137", "Địa chỉ: 201B NGUYỄN CHÍ THANH, P.12, Q.5, TP. HCM", 10.7570714,106.6575378));
        list.add(new Hospital("BỆNH VIỆN HÙNG VƯƠNG", "Điện thoại: (028)8550585", "Địa chỉ: 128 HỒNG BÀNG, P.12, Q.5, TP. HCM", 10.7558594,106.6595874 ));
        list.add(new Hospital("BỆNH VIỆN ĐA KHOA HOÀN MỸ", "Điện thoại: (028)9316944", "Địa chỉ: 124 TRẦN QUỐC THẢO, P.7, Q.3, TP. HCM", 10.7849268,106.6811839));
        list.add(new Hospital("VIỆN Y DƯỢC HỌC DÂN TỘC", "Điện thoại: (028)8443047", "Địa chỉ: 273-275 NGUYỄN VĂN TRỖI, P.10, Q.PN, TP. HCM", 10.7977412,106.6693917));
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //10.7625467,106.6801212,18z
        LatLng hcmus = new LatLng(10.7625467, 106.6801212);
        mMap.addMarker(new MarkerOptions().position(hcmus).title("KHTN"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 10));
    }
}
