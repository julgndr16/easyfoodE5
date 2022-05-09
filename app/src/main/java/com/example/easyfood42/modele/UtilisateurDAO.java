package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Administrateur;
import com.example.easyfood42.controleur.Client;
import com.example.easyfood42.controleur.Moderateur;
import com.example.easyfood42.controleur.Restaurateur;
import com.example.easyfood42.controleur.Utilisateur;

public class UtilisateurDAO {

	private static String base = "BDeasyfood";
	private static int version = 1;
	private BdSQLiteOpenHelper accesBD;
	
	public UtilisateurDAO(Context ct){
		accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
	}

	public Utilisateur getUtilisateurByMail(String mailU){
		Utilisateur unUtil = null;
		//, String nomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
		String sql =
				"select idU, mailU, pseudoU, passwd,  nomU,prenomU, numAdrU, nomAdrU, cpU, villeU, idTU " +
				"from utilisateur where mailU='"+mailU+"';";
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery(sql ,null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();

				unUtil = new Utilisateur(
					curseur.getLong(0),
					curseur.getString(1),
					curseur.getString(2),
					curseur.getString(3),
					curseur.getString(4),
					curseur.getString(5),
					curseur.getString(6),
					curseur.getString(7),
					curseur.getString(8),
					curseur.getString(9),
					curseur.getLong(10)
			);
			unUtil=getTypedUser(unUtil);



			Log.d("testLog","--------------------"+unUtil.getPasswd());
		}
		return unUtil;

	}

	private Client getClientByUtilisateur(Utilisateur utilisateur) {
		int noteEasyFood=0;
		String commentaireEasyFood="";
		boolean commentaireVisible=false;

		String sql = "select noteEasyFood, commentaireEasyFood, commentaireVisible from utilisateur where idU="+utilisateur.getIdU()+";";
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery(sql,null);

		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			noteEasyFood = curseur.getInt(0);
			commentaireEasyFood = curseur.getString(1);
			commentaireVisible = curseur.getInt(2) != 0;

		}

		return new Client(utilisateur,noteEasyFood,commentaireEasyFood,commentaireVisible);
	}

	private Utilisateur getTypedUser(Utilisateur utilisateur) {
		switch ((int) utilisateur.getIdTU()) {
			case 1:
				return getClientByUtilisateur(utilisateur);
			case 2:
				return new Restaurateur(utilisateur);
			case 3:
				return new Moderateur(utilisateur);
			case 4:
				return new Administrateur(utilisateur);
			default:
				return utilisateur;
		}
	}
}
