package com.example.easyfood42.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.adapter.CommandeAdapter;
import com.example.easyfood42.modele.CommandeDAO;

public class listeCommandesActivity extends AppCompatActivity {
    private CommandeDAO commandeDAO;
    private long idU;
    private long idComm;
    private TextView tv_aucunecommande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_commandes);
        commandeDAO=new CommandeDAO(this);
        setTitle("Mes commandes");

         tv_aucunecommande=findViewById(R.id.tv_zeroComm);


        Bundle bundleRecu = this.getIntent().getExtras();
        idU= (int) bundleRecu.getLong("idUser");

        if(commandeDAO.getCommandesByIdU(idU).size()>0){
            tv_aucunecommande.setVisibility(View.INVISIBLE);;
        }

        ListView listView = (ListView) findViewById(R.id.lv_commandes);
        CommandeAdapter commandeAdapter = new CommandeAdapter(this, commandeDAO.getCommandesByIdU(idU));
        listView.setAdapter(commandeAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, DetailCommandeActivity.class);
            Bundle bundle = new Bundle();
            idComm = commandeDAO.getCommandesByIdU(idU).get(i).getIdC();
            bundle.putLong("idComm", idComm);
            intent.putExtras(bundle);
            startActivity(intent);
        });


    }
}