package Fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yudha.Mahasiswa.DataMahasiswa;
import com.example.yudha.e_catalog.R;

import Model.DBManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends DialogFragment implements View.OnClickListener {
    EditText et1,et2;
    Button edit;
    String nama,jurusan;
    Bundle b;
    RecyclerView rv;
    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_edit, container, false);
        et1 = (EditText)v.findViewById(R.id.etNama);
        et2 = (EditText)v.findViewById(R.id.etJurusan);
        edit = (Button)v.findViewById(R.id.buEdit);

        b = getArguments();
        nama = b.getString("Nama");
        jurusan = b.getString("Jurusan");

        et1.setText(nama);
        et2.setText(jurusan);
        edit.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        rv = (RecyclerView)v.findViewById(R.id.recycler);
        String[] SelectionArgs = { String.valueOf(b.getInt("ID")) };
        ContentValues cv = new ContentValues();
        String etNama = et1.getText().toString();
        String etJurusan = et2.getText().toString();
        cv.put(DBManager.ColNama, etNama);
        cv.put(DBManager.ColJurusan, etJurusan);
        DBManager db = new DBManager(getContext());
        db.editData(cv,"ID=?", SelectionArgs);
        this.dismiss();
        Intent i = new Intent(getContext(), DataMahasiswa.class);
        getContext().startActivity(i);
        getActivity().finish();
        Toast.makeText(getContext(), "ID "+ b.getInt("ID") + "Telah di Ubah" , Toast.LENGTH_SHORT).show();
    }
}
