package com.example.easyfood42.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyfood42.controleur.Commande;
import com.example.easyfood42.R;
import com.example.easyfood42.modele.CommandeDAO;
import com.example.easyfood42.modele.RestoDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommandeAdapter extends BaseAdapter {
    private final Context context;
    private List<Commande> commandes;

    public CommandeAdapter(Context context, List<Commande> commandes) {
        this.context = context;
        this.commandes = commandes;
    }

    @Override
    public int getCount() {
        return commandes.size();
    }

    @Override
    public Commande getItem(int i) {
        return commandes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.commande_element, parent, false);
        }

        Commande commande = (Commande) getItem(position);
        CommandeDAO commandeDAO = new CommandeDAO(context.getApplicationContext());
        RestoDAO restoDAO = new RestoDAO(context.getApplicationContext());

        TextView tv_nameR = (TextView) convertView.findViewById(R.id.tv_quantiteP);
        TextView tv_nbPlatsPrix = (TextView) convertView.findViewById(R.id.tv_nomPlat);
        TextView tv_dateEtat = (TextView)  convertView.findViewById(R.id.tv_descP);


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = commande.getDateC();
        //Log.d("datetest",date+"");
       // Log.d("datetest",dateFormat+"");
        String dateToStr = dateFormat.format(date);

        tv_nameR.setText(restoDAO.getRestoByIdC(commande.getIdC()).getNomR());
        tv_nbPlatsPrix.setText(commandeDAO.getNbPlatsByIdC(commande.getIdC())+" plat(s) - "+commandeDAO.getPrixByIdC(commande.getIdC())+"€");
        tv_dateEtat.setText(commandeDAO.getEtatByIdC(commande.getIdC())+" - "+dateToStr);


        return convertView;
    }
}
