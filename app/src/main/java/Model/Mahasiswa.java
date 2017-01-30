package Model;

import Adapter.MahasiswaAdapter;

/**
 * Created by Yudha on 1/28/2017.
 */

public class Mahasiswa {


    private String nama;
    private String jurusan;
    private int ID;

    //Constructor
    public Mahasiswa(int ID,String nama , String jurusan){
        this.nama = nama;
        this.jurusan = jurusan;
        this.ID = ID;
    }


    //ID
    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    //Nama
    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    //Jurusan
    public String getJurusan(){
        return jurusan;
    }

    public void setJurusan(String jurusan){
        this.jurusan = jurusan;
    }
}
