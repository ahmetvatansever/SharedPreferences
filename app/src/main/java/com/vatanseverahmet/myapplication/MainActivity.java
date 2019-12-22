package com.vatanseverahmet.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sharedPreferencesUseThisActivity, sharedPreferencesUseAllActivity;
    SharedPreferences.Editor editor;

    String lvName, lvSurname, lvAge;

    EditText edtName, edtSurname, edtAge;
    Button btnSave, btnGetSharedData, btnUseAllActivity, btnSecondActivity;
    TextView tvInfo, tvInfoWithChange;
    String lvChangeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        btnSave.setOnClickListener(this);
        btnGetSharedData.setOnClickListener(this);
        btnUseAllActivity.setOnClickListener(this);
        btnSecondActivity.setOnClickListener(this);

        //getPreferences ile getSharedPreferences arasındaki farkları zaten atama yaptığımız sharedPreferences isimlerinden anlaşıldığı üzere
        //getPreferences : ile kaydedilen bilgiler sadece kaydedildiği activity içerisinde kullanılır.
        //getSharedPreferences : ile kaydedilen bilgiler tüm activityler içerisinde kullanılabilir.
        sharedPreferencesUseThisActivity = getPreferences(MODE_PRIVATE);
        sharedPreferencesUseAllActivity = getSharedPreferences("myPublicReferans", Context.MODE_PRIVATE);

    }

    public void init(){
        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtAge = findViewById(R.id.edtAge);
        btnSave = findViewById(R.id.btnSave);
        btnGetSharedData = findViewById(R.id.btnGetSharedData);
        btnUseAllActivity = findViewById(R.id.btnUseAllActivity);
        btnSecondActivity = findViewById(R.id.btnSecondActivity);
        tvInfo = findViewById(R.id.tvInfo);
        tvInfoWithChange = findViewById(R.id.tvInfoWithChange);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

                //Girilen bilgileri sadece bu activity içerisinde kullanmak üzere kaydediyoruz.
            case R.id.btnSave :
                editor = sharedPreferencesUseThisActivity.edit();
                editor.putString("NAME", edtName.getText().toString());
                editor.putString("SURNAME", edtSurname.getText().toString());
                editor.putInt("AGE", Integer.parseInt(edtAge.getText().toString()));
                editor.commit();
                Toast.makeText(MainActivity.this,"Veriler kaydedildi.",Toast.LENGTH_SHORT).show();
                break;

                //MainActivity için kaydedilen sharedPreferences bilgisini ekranda gösteriyoruz.
            case R.id.btnGetSharedData :
                lvName = getPreferences(MODE_PRIVATE).getString("NAME", "İsim yok");
                lvSurname = getPreferences(MODE_PRIVATE).getString("SURNAME", "Soyad yok");
                lvAge =  String.valueOf(getPreferences(MODE_PRIVATE).getInt("AGE", 0));
                tvInfo.setText(lvName + ' ' + lvSurname + ' ' + lvAge);
                break;

                //Girilen bilgileri TÜM activitylerde kullanmak üzere kaydediyoruz.
            case R.id.btnUseAllActivity :
                editor = sharedPreferencesUseAllActivity.edit();
                editor.putString("NAME", edtName.getText().toString());
                editor.putString("SURNAME", edtSurname.getText().toString());
                editor.putInt("AGE", Integer.parseInt(edtAge.getText().toString()));
                editor.commit();
                break;

                //İkinci aktiviteye gidiyoruz.
            case R.id.btnSecondActivity :
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}
