package com.example.easyfood42.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.controleur.Evaluer;
import com.example.easyfood42.controleur.Resto;
import com.example.easyfood42.modele.EvaluerDAO;
import com.example.easyfood42.modele.RestoDAO;
import com.example.easyfood42.modele.UtilisateurDAO;

public class EvaluationActivity extends AppCompatActivity {
    private TextView tv_evalResto;
    private EditText et_commentaire;
    private RatingBar rb_evalRecette;
    private RatingBar rb_esthetiqueP;
    private RatingBar rb_coutP;
    private RatingBar rb_qualiteNourriture;
    private RestoDAO restoDAO;
    private EvaluerDAO evaluerDAO;
    private Button b_saveEval;
    private TextView tv_erreur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        restoDAO = new RestoDAO(this);
        evaluerDAO=new EvaluerDAO(this);

        Bundle bundleRecu = this.getIntent().getExtras();
        long idUserEval=  bundleRecu.getLong("idUserEval");
        long idRestoEval=  bundleRecu.getLong("idRestoEval");
        long idCommEval= bundleRecu.getLong("idCommEval");

        tv_evalResto=findViewById(R.id.tv_evalResto);
        et_commentaire=findViewById(R.id.et_commentaire);
        rb_evalRecette=findViewById(R.id.rb_evalRecette);
        rb_esthetiqueP=findViewById(R.id.rb_esthetiqueP);
        rb_coutP=findViewById(R.id.rb_coutP);
        rb_qualiteNourriture=findViewById(R.id.rb_qualiteNourriture);
        b_saveEval=findViewById(R.id.b_saveEval);
        tv_erreur=findViewById(R.id.tv_erreur);

        tv_evalResto.setText(restoDAO.getRestoByIdR(idRestoEval).getNomR());

        b_saveEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_commentaire.getText().toString().isEmpty()){
                    tv_erreur.setVisibility(View.VISIBLE);
                } else {
                    int respectRecette=(int) rb_evalRecette.getRating();
                    int esthetique=(int) rb_esthetiqueP.getRating();
                    int cout=(int) rb_coutP.getRating();
                    int qualiteNourriture=(int) rb_qualiteNourriture.getRating();

                    Evaluer evaluer=new Evaluer(et_commentaire.getText().toString(),respectRecette,esthetique,cout,qualiteNourriture,idRestoEval,idUserEval);
                    evaluerDAO.addEvaluer(evaluer);
                    Intent intentRetour=new Intent();
                    intentRetour.putExtra("idCommande",idCommEval);
                    setResult(1,intentRetour);
                    finish();
                }

            }});





    }
}