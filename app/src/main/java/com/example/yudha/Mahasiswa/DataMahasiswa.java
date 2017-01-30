package com.example.yudha.Mahasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yudha.e_catalog.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MahasiswaAdapter;
import Model.DBManager;
import Model.Mahasiswa;

public class DataMahasiswa extends AppCompatActivity {
    RecyclerView rv;
    List<Mahasiswa> mahasiswaList = new ArrayList<>();
    TextView nama,jurusan;
    LinearLayoutManager lm;
    MahasiswaAdapter mahasiswaAdapter;
    DBManager db;
    Button refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mahasiswa);

        init();
        rv = (RecyclerView)findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        db = new DBManager(this);

        loadElements();

        refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadElements();
            }
        });

    }

    public void init(){
        nama = (TextView)findViewById(R.id.itemNama);
        jurusan = (TextView)findViewById(R.id.itemJurusan);
    }

    public void loadElements(){
        mahasiswaList = db.semuaData();
        mahasiswaAdapter = new MahasiswaAdapter(DataMahasiswa.this,mahasiswaList);
        rv.setAdapter(mahasiswaAdapter);
        mahasiswaAdapter.notifyDataSetChanged();
    }
}
