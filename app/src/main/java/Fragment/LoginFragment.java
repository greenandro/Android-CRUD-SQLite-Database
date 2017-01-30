package Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yudha.Mahasiswa.MainActivity;
import com.example.yudha.e_catalog.R;

import Session.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */

//Implement On Click Login
public class LoginFragment extends DialogFragment implements View.OnClickListener {
    View view;
    Button bc,bl;
    EditText et1,et2;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_blank, container, false);
        bl = (Button)view.findViewById(R.id.login);
        bl.setOnClickListener(this);

        bc = (Button)view.findViewById(R.id.cancel);
        //Button Cancel Menutup Dialog
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sm = new SessionManager(getContext());
                String Data = sm.loadData();
                Toast.makeText(getContext(), Data , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    //Login dan Mendapatkan Username yang bakal disimpan
    public void onClick(View v) {
        et1 = (EditText) view.findViewById(R.id.username);
        et2 = (EditText) view.findViewById(R.id.password);
        SessionManager sm = new SessionManager(getContext());
        String username = et1.getText().toString();
        String password = et2.getText().toString();

        //Check Apakah Editext username dan password lebih dari 0
        if (username.trim().length() > 0 && password.trim().length() > 0) {
            if (username.equals("admin") && password.equals("admin") || username.equals("yudha") && password.equals("yudha") ) {
                sm.saveData(username);
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                ((Activity)getContext()).finish();
            } else {
                Toast.makeText(getContext(), "Password dan Username Salah", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getContext(), "Password dan Username Harus Diisi!", Toast.LENGTH_SHORT).show();
        }
    }


}
