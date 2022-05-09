package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Commande;
import com.example.easyfood42.controleur.Contenir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ContenirDAO {
    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public ContenirDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Contenir getContenirByIdCAndIdP(long idC, long idP){
        Contenir unContenir = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select idC,idP,qteComm from contenir where idC="+idC+" AND idP="+idP+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unContenir = new Contenir(idC,idP,curseur.getInt(2));
        }
        return unContenir;
    }

    public ArrayList<Contenir> getContenirsByIdC(long idC){
        Cursor curseur;
        String req = "select idC,idP,qteComm from contenir where idC="+idC+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToContenirArrayList(curseur);
    }

    private ArrayList<Contenir> cursorToContenirArrayList(Cursor curseur){
        ArrayList<Contenir> listeContenir = new ArrayList<Contenir>();

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            listeContenir.add(new Contenir(curseur.getLong(0), curseur.getLong(1),curseur.getInt(2)));
            curseur.moveToNext();
        }

        return listeContenir;
    }

}
