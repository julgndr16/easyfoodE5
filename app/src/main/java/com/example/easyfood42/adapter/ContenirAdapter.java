package com.example.easyfood42.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.controleur.Contenir;
import com.example.easyfood42.controleur.Plat;
import com.example.easyfood42.modele.ContenirDAO;
import com.example.easyfood42.modele.PlatDAO;

import java.util.List;

public class ContenirAdapter extends BaseAdapter {
    private final Context context;
    private List<Contenir> contenirs;

    public ContenirAdapter(Context context, List<Contenir> contenirs) {
        this.context = context;
        this.contenirs = contenirs;
    }
    @Override
    public int getCount() {
        return contenirs.size();
    }

    @Override
    public Contenir getItem(int i) {
        return contenirs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.plat_element, parent, false);
        }

        Contenir contenir = (Contenir) getItem(position);


        TextView tv_quantiteP = (TextView) convertView.findViewById(R.id.tv_quantiteP);
        TextView tv_nomPlat = (TextView) convertView.findViewById(R.id.tv_nomPlat);
        TextView tv_descP = (TextView)  convertView.findViewById(R.id.tv_descP);
        TextView tv_totalPlat = (TextView)  convertView.findViewById(R.id.tv_totalPlat);

        //ContenirDAO contenirDAO=new ContenirDAO(context.getApplicationContext());
        PlatDAO platDAO =new PlatDAO(context.getApplicationContext());

        Plat plat = platDAO.getPlatByIdP(contenir.getIdP());
        tv_quantiteP.setText("x"+String.valueOf(contenir.getQteComm()));
        tv_nomPlat.setText(plat.getNomP()+" - "+plat.getPrixClientP()+"€");
        tv_descP.setText(plat.getDescriptionP());
        tv_totalPlat.setText(String.valueOf(plat.getPrixClientP()*contenir.getQteComm())+"€");

        return convertView;
    }


}
