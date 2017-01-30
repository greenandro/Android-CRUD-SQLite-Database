package com.example.yudha.Mahasiswa;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yudha.e_catalog.R;

import Model.DBManager;
import Session.SessionManager;
import Fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        DBManager db = new DBManager(this);
        isLogin();

    }

    public void buLogin(View v){
        FragmentManager fm = getSupportFragmentManager();
        LoginFragment lf = new LoginFragment();
        lf.show(fm, "Show Fragment");
    }

    public void isLogin(){
        SessionManager sm = new SessionManager(getApplicationContext());
        if(sm.checkLogin()){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }
}
