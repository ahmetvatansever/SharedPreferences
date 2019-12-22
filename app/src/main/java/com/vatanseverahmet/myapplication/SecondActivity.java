package com.vatanseverahmet.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    SharedPreferences getSharedPreferences;

    Button btnGetPublicSharedData;
    TextView tvInfoWithChangeSecond;
    String lvName, lvSurname, lvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnGetPublicSharedData = findViewById(R.id.btnGetPublicSharedData);
        tvInfoWithChangeSecond = findViewById(R.id.tvInfoWithChangeSecond);

        //Kaydedilmiş sharedPreferences'ı aynı isimle sharedPreferense tanımlıyoruz.
        getSharedPreferences = this.getSharedPreferences("myPublicReferans", Context.MODE_PRIVATE);

        btnGetPublicSharedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvName = getSharedPreferences.getString("NAME","İsim yok");
                lvSurname = getSharedPreferences.getString("SURNAME", "Soyad yok");
                lvAge =  String.valueOf(getSharedPreferences.getInt("AGE", 0));
                tvInfoWithChangeSecond.setText(lvName + ' ' + lvSurname + ' ' + lvAge);
            }
        });
    }
}
