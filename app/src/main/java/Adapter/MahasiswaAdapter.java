package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudha.Mahasiswa.DataMahasiswa;
import com.example.yudha.e_catalog.R;
import java.util.ArrayList;
import java.util.List;
import Model.DBManager;
import Model.Mahasiswa;
import Fragment.EditFragment;
/**
 * Created by Yudha on 1/28/2017.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    Context context;
    DBManager db;
    EditFragment ef;
    List<Mahasiswa> mahasiswaList = new ArrayList<>();

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList){
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView nama,jurusan;
        Button hapus,edit;
        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView.findViewById(R.id.itemNama);
            jurusan = (TextView)itemView.findViewById(R.id.itemJurusan);
            hapus = (Button) itemView.findViewById(R.id.hapus);
            edit = (Button)itemView.findViewById(R.id.edit);
        }
    }

    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa,null);
       MahasiswaViewHolder mv = new MahasiswaViewHolder(v);
       return mv;
    }

    @Override
    public void onBindViewHolder(final MahasiswaAdapter.MahasiswaViewHolder holder, final int position) {
        holder.nama.setText(mahasiswaList.get(position).getNama());
        holder.jurusan.setText(mahasiswaList.get(position).getJurusan());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBManager(context);
                String[] ID  = { String.valueOf(mahasiswaList.get(position).getID()) };
                int count = db.hapusData("ID=?",ID);
                notifyItemChanged(position);
                if(count > 0){
                    Intent i = new Intent(context , DataMahasiswa.class);
                    context.startActivity(i);
                    ((Activity)context).finish();
                    Toast.makeText(context, "ID = "+mahasiswaList.get(position).getID() + " Berhasil di Hapus " , Toast.LENGTH_SHORT ).show();
                }
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                ef = new EditFragment();
                Bundle b = new Bundle();
                b.putString("Nama", mahasiswaList.get(position).getNama());
                b.putString("Jurusan", mahasiswaList.get(position).getJurusan());
                b.putInt("ID", mahasiswaList.get(position).getID());
                ef.setArguments(b);
                ef.show(fm,"my Dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }
}
