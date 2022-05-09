package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;

import com.example.easyfood42.controleur.Resto;

public class RestoDAO {
    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public RestoDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Resto getRestoByIdR(long idR){
        Resto unResto = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from resto where idR="+idR+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unResto = new Resto(idR,curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4),curseur.getString(5),curseur.getLong(6));
        }
        return unResto;
    }

    public Resto getRestoByIdC(long idC){
        Resto unResto = null;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select resto.* from commande inner join contenir on contenir.idC=commande.idC inner join plat on plat.idP=contenir.idP inner join resto on plat.idR=resto.idR where commande.idC="+idC+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();

            unResto = new Resto(curseur.getLong(0),curseur.getString(1),curseur.getString(2),curseur.getString(3),curseur.getString(4),curseur.getString(5),curseur.getLong(6));
        }
        return unResto;

    }
}
