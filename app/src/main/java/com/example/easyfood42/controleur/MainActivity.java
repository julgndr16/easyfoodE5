package com.example.easyfood42.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.client.listeCommandesActivity;
import com.example.easyfood42.modele.TypeUtilisateurDAO;
import com.example.easyfood42.modele.UtilisateurDAO;

public class MainActivity extends AppCompatActivity {
    private Utilisateur utilConnecte;
    private Button b_connexion;
    private EditText et_mailU;
    private EditText et_passwd;
    private UtilisateurDAO unUtilDAO;
    private TextView tv_echecConnex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_connexion = findViewById(R.id.b_connexion);
        et_mailU = findViewById(R.id.et_mailU);
        et_passwd = findViewById(R.id.et_passwd);
        tv_echecConnex = findViewById(R.id.tv_echecConnex);

        unUtilDAO = new UtilisateurDAO(this);

        b_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilConnecte = unUtilDAO.getUtilisateurByMail(et_mailU.getText().toString());
                //Log.d("testLog","mdp : "+utilConnecte.getPrenomU() + utilConnecte.getMailU());

                if (utilConnecte != null && utilConnecte.verifPasswd(et_passwd.getText().toString())){
                    Log.d("testLog","utilisateur reconnu. L'utilisateur connecté est stocké dans utilConnecte");
                    TypeUtilisateurDAO unTUDAO = new TypeUtilisateurDAO(MainActivity.this);
                    TypeUtilisateur leTypeUtil = unTUDAO.getTypeUtilisateurById(utilConnecte.getIdTU());
                    Log.d("testLog",leTypeUtil.getLibelleTU());

                    switch(leTypeUtil.getLibelleTU()){
                        case "client":
                            Intent intent = new Intent(getApplicationContext(), listeCommandesActivity.class);
                            Bundle bundleListeComm = new Bundle();
                            bundleListeComm.putLong("idUser",utilConnecte.getIdU());
                            intent.putExtras(bundleListeComm);
                            startActivity(intent);
                            break;

                        case "restaurateur":

                            break;

                        case "moderateur":

                            break;
                        case "administrateur":

                            break;
                    }
                }
                else{
                    Log.d("testLog","utilisateur non reconnu");
                    utilConnecte=null;
                    tv_echecConnex.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}