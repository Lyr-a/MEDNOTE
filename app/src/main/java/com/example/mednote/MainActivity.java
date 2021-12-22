package com.example.mednote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mednote.logi.LoginActivity;
import com.example.mednote.sinto.SintomasFragment;
import com.example.mednote.trat.TratamentoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    boolean Log = false;
    int Lo = 0;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.FcvMain, new SintomasFragment()).commit();

        //region VIEWS

        bottomNavigationView = findViewById(R.id.BtmnavMain);

        //endregion

        //region BOTTOMNAV
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch(item.getItemId()){
                    case R.id.Item1:
                        fragment = new SintomasFragment();
                        break;
                    case R.id.Item2:
                        fragment = new TratamentoFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FcvMain, fragment).commit();
                return true;
            }
        });
        //endregion

        //region LOGIN
        Login();
        //endregion
    }

    //region LOGIN

    public void Login (){
        if (Log == false){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            Log = true;
            startActivityForResult(i, NEW_ITEM_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK){

            }
        }
    }
    //endregion

}