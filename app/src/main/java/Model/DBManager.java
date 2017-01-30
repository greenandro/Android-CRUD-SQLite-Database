package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MahasiswaAdapter;

/**
 * Created by Yudha on 1/27/2017.
 */

public class DBManager {
    Context context;
    private SQLiteDatabase sqLiteDatabase;
    static final String DB_NAME = "Mahasiswa";
    static final String TB_NAME = "Data_Mahasiswa";
    public static final String ColNama = "Nama";
    public static final String ColJurusan = "Jurusan";
    public static final int ColID = 0;
    static final int DB_VERSION = 1;

    static final String CreateTable = "Create Table IF NOT EXISTS " + TB_NAME + "(ID Integer PRIMARY KEY AUTOINCREMENT,"+ ColNama + " text,"+
             ColJurusan + " text)";


    //Pembantu Membuat Database dan Table
    static class DatabaseHelper extends SQLiteOpenHelper {
        Context context;
        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
        }

        // FUNGSI UNTUK MEMBUAT DATABASENYA
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context, "Database has Created!", Toast.LENGTH_SHORT).show();
        }

        // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS "+ TB_NAME);
            onCreate(db);
        }
    }

    public DBManager(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public long TambahData(ContentValues contentValues){
        long ID = sqLiteDatabase.insert(TB_NAME, "", contentValues);
        return ID;
    }

    public List<Mahasiswa> semuaData(){
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String query = "SELECT * FROM " + TB_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Mahasiswa mahasiswa = new Mahasiswa(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                mahasiswaList.add(mahasiswa);

            }while(cursor.moveToNext());
        }

        return mahasiswaList;
    }

    public int hapusData(String Selection,String[] id){
        int count = sqLiteDatabase.delete(TB_NAME, Selection,id);
        return count;
    }

    public int editData(ContentValues cv,String Selection, String[] id){
        int count = sqLiteDatabase.update(TB_NAME, cv, Selection, id);
        return count;
    }


    public Cursor query(String[] Projection, String Selection,String[] SelectionArgs, String SortOrder){
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
        sqb.setTables(TB_NAME);
        Cursor cursor = sqb.query(sqLiteDatabase,Projection,Selection,SelectionArgs,null,null,SortOrder);

        if(cursor.moveToFirst()){
            do{
                Mahasiswa mahasiswa = new Mahasiswa(cursor.getInt(0),cursor.getString(1),cursor.getString(1));
                mahasiswaList.add(mahasiswa);
            }while(cursor.moveToNext());
        }

        return cursor;
    }

}
