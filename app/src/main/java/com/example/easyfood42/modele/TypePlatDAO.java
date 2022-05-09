package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;

import com.example.easyfood42.controleur.TypePlat;

public class TypePlatDAO {
    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public TypePlatDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public TypePlat getTypePlatByIdTP(long idTP){
        TypePlat unTypePlat = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from typeplat where idTP="+idTP+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unTypePlat = new TypePlat(idTP,curseur.getString(1));
        }
        return unTypePlat;
    }
}
