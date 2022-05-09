package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.easyfood42.controleur.Evaluer;

public class EvaluerDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public EvaluerDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Evaluer getEvaluerByIdUAndIdR(long idU, long idR){
        Evaluer uneEval = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select commentaire, respectRecette,esthetiquePlat,cout,qualiteNourriture es from evaluer where idU="+idU+" AND idR="+idR+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            uneEval = new Evaluer(curseur.getString(0),curseur.getInt(1),curseur.getInt(2),curseur.getInt(3),curseur.getInt(4),idR,idU);
        }
        return uneEval;
    }

    public void addEvaluer(Evaluer evaluer){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String req = "insert into evaluer (idU,idR, commentaire,commentaireVisible,commentaireModere, respectRecette,esthetiquePlat,cout,qualiteNourriture) values("
                +evaluer.getIdU()+","+evaluer.getIdR()+",'"+evaluer.getCommentaire()+"',0,0,"+evaluer.getRespectRecette()+","+evaluer.getEsthetiquePlat()+","+evaluer.getCout()+","+evaluer.getQualiteNourriture()+");";

        bd.execSQL(req);
        bd.close();
    }

}
