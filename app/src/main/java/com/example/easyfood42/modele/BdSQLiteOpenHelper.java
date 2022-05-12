package com.example.easyfood42.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {

	private String table_type_utilisateur="create table type_utilisateur ("
			+ "idTU INTEGER PRIMARY KEY,"
			+ "libelleTU TEXT NOT NULL);";

	private String table_utilisateur="create table utilisateur ("
			+ "idU INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "mailU TEXT NOT NULL,"
			+ "pseudoU TEXT,"
			+ "passwd TEXT NOT NULL,"
			+ "nomU TEXT,"
			+ "prenomU TEXT,"
			+ "numAdrU TEXT,"
			+ "nomAdrU TEXT,"
			+ "cpU TEXT,"
			+ "villeU TEXT,"
			+ "noteEasyFood INTEGER,"
			+ "commentaireEasyFood TEXT,"
			+ "commentaireVisible INTEGER,"
			+ "idTU INTEGER,"
			+ "foreign key (idTU) references type_utilisateur(idTU));";

	private String table_commande="create table commande ("
			+ "idC INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "dateC TEXT,"
			+ "commentaireClientC TEXT,"
			+ "dateLivrC TEXT,"
			+ "modeReglementC TEXT,"
			+ "commandePreparee INTEGER,"
			+ "commandeLivree INTEGER,"
			+ "idU INTEGER,"
			+ "foreign key (idU) references utilisateur(idU));";

	private String table_contenir="create table contenir ("
			+ "idC INTEGER,"
			+ "idP INTEGER,"
			+ "qteComm TEXT,"
			+ "primary key(idC,idP),"
			+ "foreign key (idC) references commande(idC),"
			+ "foreign key (idP) references plat(idP));";

	private String table_typeplat="create table typeplat ("
			+ "idTP INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "libelleTP TEXT);";

	private String table_plat="create table plat ("
			+ "idP INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "nomP TEXT NOT NULL,"
			+ "prixFournisseurP REAL,"
			+ "prixClientP REAL,"
			+ "platVisible INTEGER,"
			+ "photoP TEXT,"
			+ "descriptionP TEXT,"
			+ "idR INTEGER,"
			+ "idTP INTEGER,"
			+ "foreign key (idTP) references typeplat(idR),"
			+ "foreign key (idR) references resto(idR));";

	private String table_resto="create table resto ("
			+ "idR INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "nomR TEXT NOT NULL,"
			+ "numAdrR TEXT,"
			+ "nomAdrR TEXT,"
			+ "cpR TEXT,"
			+ "villeR TEXT,"
			+ "idU INTEGER,"
			+ "foreign key (idU) references utilisateur(idU));";

	private String table_evaluer="create table evaluer ("
			+ "idU INTEGER,"
			+ "idR INTEGER,"
			+ "commentaire TEXT,"
			+ "commentaireVisible INTEGER,"
			+ "commentaireModere INTEGER,"
			+ "respectRecette INTEGER,"
			+ "esthetiquePlat INTEGER,"
			+ "cout INTEGER,"
			+ "qualiteNourriture INTEGER,"
			+ "primary key(idU,idR),"
			+ "foreign key (idU) references utilisateur(idU),"
			+ "foreign key (idR) references resto(idR));";


	public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(table_type_utilisateur);
		db.execSQL(table_utilisateur);
		db.execSQL(table_commande);
		db.execSQL(table_contenir);
		db.execSQL(table_typeplat);
		db.execSQL(table_plat);
		db.execSQL(table_resto);
		db.execSQL(table_evaluer);

		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(1,'client');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(2,'restaurateur');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(3,'moderateur');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(4,'administrateur');");


		String mdpChiffre = BdSQLiteOpenHelper.md5("motdepasse");

		//db.execSQL("insert into utilisateur (mailU,passwd,noteEasyFood, commentaireEasyFood, commentaireVisible, idTU) values('client1@sio.fr','"+mdpChiffre+"',4,'très bien',1,1);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('client1@sio.fr','"+mdpChiffre+"',1);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('restaurateur1@sio.fr','"+mdpChiffre+"',2);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('moderateur1@sio.fr','"+mdpChiffre+"',3);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('client2@sio.fr','"+mdpChiffre+"',1);");


		db.execSQL("insert into typeplat (idTP, libelleTP) values(1,'viande rouge');");
		db.execSQL("insert into typeplat (idTP, libelleTP) values(2,'burger');");
		db.execSQL("insert into typeplat (idTP, libelleTP) values(3,'pizza');");
		db.execSQL("insert into typeplat (idTP, libelleTP) values(4,'pates');");

		db.execSQL("insert into resto (idR, nomR, numAdrR, nomAdrR, cpR, villeR, idU) values(1,'entrepote','4bis','rue de la compote','64300','Orthez',2);");
		db.execSQL("insert into resto (idR, nomR, numAdrR, nomAdrR, cpR, villeR, idU) values(2,'imposteur','36','rue du général pétain','68540','Bollwiller',2);");

		db.execSQL("insert into plat (nomP, prixFournisseurP,prixClientP,platVisible,photoP,descriptionP,idR,idTP) values('entrecote',10.0,15.0,1,'entrecote.jpg','belle entrecôte de 250g !',1,1);");
		db.execSQL("insert into plat (nomP, prixFournisseurP,prixClientP,platVisible,photoP,descriptionP,idR,idTP) values('burger savoyard',8.0,10.0,1,'burger-savoyard.jpg','bon burger à la raclette !',1,2);");
		db.execSQL("insert into plat (nomP, prixFournisseurP,prixClientP,platVisible,photoP,descriptionP,idR,idTP) values('burger veggie',8.0,10.0,1,'burger-veggie.jpg','bon burger veggie !',1,2);");

		db.execSQL("insert into plat (nomP, prixFournisseurP,prixClientP,platVisible,photoP,descriptionP,idR,idTP) values('Pâtes bolo',5.0,8.0,1,'pates-bolo.jpg','Des bonnes pâtes bolo !',2,4);");
		db.execSQL("insert into plat (nomP, prixFournisseurP,prixClientP,platVisible,photoP,descriptionP,idR,idTP) values('Pâtes carbo',5.5,8.5,1,'pates-carbo.jpg','Des bonnes pâtes carbo !',2,4);");

		db.execSQL("insert into commande (dateC, commentaireClientC, dateLivrC, modeReglementC, commandePreparee, commandeLivree, idU) values('2022-02-01 12:20:00','un peu de sel svp','2022-02-01 12:45:00','carte bleue',1,1,1);");
		db.execSQL("insert into commande (dateC, commentaireClientC, dateLivrC, modeReglementC, commandePreparee, commandeLivree, idU) values('2022-06-01 12:20:00','rien à signaler','2022-06-01 12:45:00','liquide',1,1,1);");

		db.execSQL("insert into commande (dateC, commentaireClientC, dateLivrC, modeReglementC, commandePreparee, commandeLivree, idU) values('2022-04-01 12:20:00','des couverts svp','2022-04-01 12:45:00','cheque',1,1,4);");

		db.execSQL("insert into contenir (idC, idP, qteComm) values(1,1,2);");
		db.execSQL("insert into contenir (idC, idP, qteComm) values(1,2,1);");

		db.execSQL("insert into contenir (idC, idP, qteComm) values(2,4,3);");

		db.execSQL("insert into contenir (idC, idP, qteComm) values(3,4,1);");

		db.execSQL("insert into evaluer (idU,idR, commentaire,commentaireVisible,commentaireModere, respectRecette,esthetiquePlat,cout,qualiteNourriture) values(1,1,'très bon resto, parfait',1,1,5,4,3,4);");
		//db.execSQL("insert into evaluer (idU,idR, commentaire,commentaireVisible,commentaireModere, respectRecette,esthetiquePlat,cout,qualiteNourriture) values(1,2,'resto à revoir',0,0,2,1,2,1);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public static final String md5(final String s) {
		final String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest
					.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
