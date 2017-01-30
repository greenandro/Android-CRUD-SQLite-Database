package com.example.yudha.Mahasiswa;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yudha.e_catalog.R;

import Model.DBManager;
import Model.Mahasiswa;

public class TambahData extends AppCompatActivity {
    private EditText nama , jurusan;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        nama = (EditText)findViewById(R.id.nama);
        jurusan = (EditText)findViewById(R.id.jurusan);

        dbManager = new DBManager(this);
    }

    public void buTambah(View v){
        ContentValues cv = new ContentValues();

        String Nama = nama.getText().toString();
        String Jurusan = jurusan.getText().toString();
        int Id = 0;
        Mahasiswa mahasiswa = new Mahasiswa(Id,Nama,Jurusan);
        cv.put(DBManager.ColNama , mahasiswa.getNama());
        cv.put(DBManager.ColJurusan, mahasiswa.getJurusan());

        long ID = dbManager.TambahData(cv);

        //Validasi
        if(Nama.isEmpty()){
            Toast.makeText(this , "Harap Isi Nama", Toast.LENGTH_SHORT).show();
            nama.requestFocus();
        }else if(Jurusan.isEmpty()){
            Toast.makeText(this , "Harap Isi Jurusan", Toast.LENGTH_SHORT).show();
            jurusan.requestFocus();
        }else if(Nama.isEmpty() && Jurusan.isEmpty()){
            Toast.makeText(this , "Form Masih Kosong Harap Diisi!", Toast.LENGTH_SHORT).show();
            nama.requestFocus();
        }else{
            if(ID > 0){
                Toast.makeText(getApplicationContext(),"Data Berhasil Di Tambah", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Data Gagal Di Tambah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
