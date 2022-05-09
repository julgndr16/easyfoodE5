package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Commande;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommandeDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public CommandeDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Commande getCommandeByIdC(long idC){
        Commande uneCommande = null;
        boolean commandePreparee;
        boolean commandeLivree;

        String dateCString;
        Date dateC=null;
        String dateLivrCString;
        Date dateLivrC=new Date();

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from commande where idC="+idC+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            commandePreparee=curseur.getInt(5)!=0;
            commandeLivree=curseur.getInt(6)!=0;

            dateCString=curseur.getString(1);
            dateLivrCString=curseur.getString(3);
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {

                dateC = dateParser.parse(dateCString);
                dateLivrC = dateParser.parse(dateLivrCString);
                //SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");

            }catch(Exception e){

            }


            uneCommande = new Commande(idC, dateC,curseur.getString(2),dateLivrC,curseur.getString(4),commandePreparee,commandeLivree,curseur.getLong(7));
        }
        return uneCommande;
    }

    public String getEtatByIdC(long idC){
        String etat="etats";
        boolean commandeLivree=false;
        boolean commandePreparee=false;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select commandeLivree,commandePreparee from commande where commande.idC="+idC+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            commandeLivree=curseur.getInt(0)!=0;
            commandePreparee=curseur.getInt(1)!=0;
        }

        if(commandeLivree && commandePreparee){
            etat="Terminée";
        } else if(!commandeLivree && commandePreparee){
            etat="Préparée";
        } else if(!commandeLivree && !commandePreparee){
            etat="En cours préparation";
        }
        return etat;
    }

    //récupère le prix total de la commande passée en paramètre
    public double getPrixByIdC(long idC){
        double prix=0;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select SUM(prixClientP*qteComm) from commande inner join contenir on contenir.idC=commande.idC inner join plat on plat.idP=contenir.idP where commande.idC="+idC+" GROUP BY contenir.idC;",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            prix=curseur.getDouble(0);

        }
        return prix;

    }

    //récupère le nombre de plats de la commande passée en paramètre
    public int getNbPlatsByIdC(long idC){
        int nbPlats=0;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select COUNT(qteComm) from commande inner join contenir on contenir.idC=commande.idC inner join plat on plat.idP=contenir.idP where commande.idC="+idC+" GROUP BY contenir.idC;",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            nbPlats=curseur.getInt(0);

        }
        return nbPlats;
    }

    public ArrayList<Commande> getCommandes(){
        Cursor curseur;
        String req = "select * from commande";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToCommandeArrayList(curseur);
    }

    public ArrayList<Commande> getCommandesByIdU(long idU){
        Cursor curseur;
        String req = "select * from commande where idU="+idU+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToCommandeArrayList(curseur);
    }

    private ArrayList<Commande> cursorToCommandeArrayList(Cursor curseur){
        ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
        boolean commandePreparee;
        boolean commandeLivree;

        String dateCString;
        Date dateC=null;
        String dateLivrCString;
        Date dateLivrC=null;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            commandePreparee=curseur.getInt(5)!=0;
            commandeLivree=curseur.getInt(6)!=0;

            dateCString=curseur.getString(1);
            dateLivrCString=curseur.getString(3);
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                //Log.d("datetest","bonjour");
                dateC = dateParser.parse(dateCString);
                dateLivrC = dateParser.parse(dateLivrCString);
                //SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");

            }catch(Exception e){
                //Log.d("datetest","au revoir");
            }
            listeCommandes.add(new Commande(curseur.getLong(0), dateC,curseur.getString(2),dateLivrC,curseur.getString(4),commandePreparee,commandeLivree,curseur.getLong(7)));
            curseur.moveToNext();
        }

        return listeCommandes;
    }

}
