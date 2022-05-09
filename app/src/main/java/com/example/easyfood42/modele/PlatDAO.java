package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Commande;
import com.example.easyfood42.controleur.Plat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlatDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public PlatDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Plat getPlatByIdP(long idP){
        Plat unPlat = null;
        boolean platVisible;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from plat where idP="+idP+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            platVisible=curseur.getInt(4)!=0;
            unPlat = new Plat(idP,curseur.getString(1),curseur.getLong(2),curseur.getLong(3),platVisible,curseur.getString(5),curseur.getString(6),curseur.getLong(7),curseur.getLong(8));
        }
        return unPlat;
    }

    public ArrayList<Plat> getPlatsByIdC(long idC){
        Cursor curseur;
        String req = "select plat.* from commande inner join contenir on contenir.idC=commande.idC inner join plat on plat.idP=contenir.idP where idC="+idC+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPlatArrayList(curseur);
    }

    private ArrayList<Plat> cursorToPlatArrayList(Cursor curseur){
        ArrayList<Plat> listePlats = new ArrayList<Plat>();
        boolean platVisible;
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            platVisible=curseur.getInt(4)!=0;
            listePlats.add(new Plat(curseur.getLong(0),curseur.getString(1),curseur.getDouble(2),curseur.getDouble(3),platVisible,curseur.getString(5),curseur.getString(6),curseur.getLong(7),curseur.getLong(8)));
            curseur.moveToNext();
        }

        return listePlats;
    }
}
