package Session;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

/**
 * Created by Yudha on 1/26/2017.
 */

public class SessionManager {
    SharedPreferences sessionManager;
    Context context;
    //Constructor
    public SessionManager(Context context){
         sessionManager = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }

    //Fungsi Menyimpan Data Saat Login
    public void saveData(String UserName){
        SharedPreferences.Editor editor = sessionManager.edit();
        editor.putString("UserName", UserName);
        editor.putBoolean("IsLoggedIn", true);
        editor.commit();
    }

    //Check Login bernilai true or false
    public boolean checkLogin(){
        return sessionManager.getBoolean("IsLoggedIn", false);
    }

    //Hanya Check Session Ada atau Tidak
    public String loadData(){
        String FileContent = sessionManager.getString("UserName","No Username");
        FileContent += "IsLoggedIn"+sessionManager.getBoolean("IsLoggedIn", false);
        return FileContent;
    }

    //Mengambil seluruh data yang di save
    public ArrayList getDetail(){
        ArrayList add = new ArrayList();
        add.add(sessionManager.getString("UserName","No Session"));
        add.add(sessionManager.getBoolean("IsLoggedIn",false));
        return add;
    }

    //Menghapus Session
    public void isLogout(){
        SharedPreferences.Editor editor = sessionManager.edit();
        editor.clear();
        editor.commit();

    }

}
