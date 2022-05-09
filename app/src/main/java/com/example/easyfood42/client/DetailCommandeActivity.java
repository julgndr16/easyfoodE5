package com.example.easyfood42.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.adapter.CommandeAdapter;
import com.example.easyfood42.adapter.ContenirAdapter;

import com.example.easyfood42.controleur.Commande;
import com.example.easyfood42.controleur.MainActivity;
import com.example.easyfood42.controleur.Resto;
import com.example.easyfood42.controleur.TypeUtilisateur;
import com.example.easyfood42.modele.CommandeDAO;
import com.example.easyfood42.modele.ContenirDAO;
import com.example.easyfood42.modele.EvaluerDAO;
import com.example.easyfood42.modele.PlatDAO;
import com.example.easyfood42.modele.RestoDAO;
import com.example.easyfood42.modele.TypeUtilisateurDAO;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailCommandeActivity extends AppCompatActivity {
    private ContenirDAO contenirDAO;
    private CommandeDAO commandeDAO;
    private EvaluerDAO evaluerDAO;
    private RestoDAO restoDAO;
    private ListView listView;
    private TextView tv_nomRestoC;
    private TextView tv_etatDateHeureC;
    private TextView tv_commentaireC;
    private TextView tv_modeReglementC;
    private TextView tv_totalPrixC;
    private Resto unResto;
    private Commande commande;
    private TextView tv_commentaire;
    private RatingBar rb_respectRecette;
    private RatingBar rb_esthetique;
    private RatingBar rb_cout;
    private RatingBar rb_qualiteN;

    private Button b_evaluation;
    private LinearLayout ll_evaluationRB;
    private LinearLayout ll_evaluationTitle;
    private LinearLayout ll_detailCOMMANDE;

    private Date dateC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commande);
        contenirDAO = new ContenirDAO(this);
        commandeDAO = new CommandeDAO(this);
        restoDAO = new RestoDAO(this);
        evaluerDAO=new EvaluerDAO(this);

        Bundle bundleRecu = this.getIntent().getExtras();
        long idComm= (int) bundleRecu.getLong("idComm");
        commande=commandeDAO.getCommandeByIdC(idComm);
        unResto=restoDAO.getRestoByIdC(idComm);


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateC = commande.getDateC();
        String dateToStr = dateFormat.format(dateC);


        tv_nomRestoC=findViewById(R.id.tv_nomRestoC);
        tv_etatDateHeureC=findViewById(R.id.tv_etatDateHeureC);
        tv_commentaireC=findViewById(R.id.tv_commentaireC);
        tv_modeReglementC=findViewById(R.id.tv_modeReglementC);
        tv_totalPrixC=findViewById(R.id.tv_totalPrixC);
        tv_commentaire=findViewById(R.id.tv_commentaire);
        rb_respectRecette=findViewById(R.id.rb_respectRecette);
        rb_esthetique=findViewById(R.id.rb_esthetique);
        rb_cout=findViewById(R.id.rb_cout);
        rb_qualiteN=findViewById(R.id.rb_qualiteN);
        b_evaluation=findViewById(R.id.b_evaluation);
        ll_evaluationRB=findViewById(R.id.ll_evaluationRB);
        ll_evaluationTitle=findViewById(R.id.ll_evaluationTitle);
        ll_detailCOMMANDE=findViewById(R.id.ll_detailCOMMANDE);



        tv_nomRestoC.setText(unResto.getNomR());
        tv_etatDateHeureC.setText(commandeDAO.getEtatByIdC(idComm)+" - "+dateToStr);
        tv_commentaireC.setText(tv_commentaireC.getText().toString()+commande.getCommentaireClientC());
        tv_modeReglementC.setText(tv_modeReglementC.getText().toString()+commande.getModeReglementC());
        tv_totalPrixC.setText(tv_totalPrixC.getText().toString()+commandeDAO.getPrixByIdC(idComm)+"€");

        if(evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR())!=null) {
            refreshEvaluation();
        } else {
            ll_evaluationRB.setVisibility(View.INVISIBLE);

            if(!commande.isCommandeLivree()){
                ll_detailCOMMANDE.removeView(b_evaluation);
                TextView view=new TextView(this);
                view.setText("Vous ne pouvez pas évaluer le resto tant que ce n'est pas livré.");
                ll_evaluationTitle.addView(view);
            } else {
                b_evaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), EvaluationActivity.class);
                        Bundle bundleListeComm = new Bundle();
                        bundleListeComm.putLong("idUserEval",commande.getIdU());
                        bundleListeComm.putLong("idRestoEval",unResto.getIdR());
                        bundleListeComm.putLong("idCommEval",idComm);
                        intent.putExtras(bundleListeComm);
                        startActivityForResult(intent,1);

                }});

            }

        }


        listView = (ListView) findViewById(R.id.lv_platsC);
        ContenirAdapter contenirAdapter = new ContenirAdapter(this, contenirDAO.getContenirsByIdC(idComm));
        listView.setAdapter(contenirAdapter);
        /*listView.setOnItemClickListener((adapterView, view, i, l) -> {

        });*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("test","onActivityResult");
        switch(resultCode){
            case 1:
                Log.d("test","sWitch");
                Bundle bundle=data.getExtras();
                long idC=bundle.getLong("idCommande");

                ll_evaluationRB.setVisibility(View.VISIBLE);
                refreshEvaluation();
                break;
            default:
                break;
        }


    }

    private void refreshEvaluation(){
        float respectRecette=evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR()).getRespectRecette();
        float esthetique=evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR()).getEsthetiquePlat();
        float cout=evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR()).getCout();
        float qualiteN=evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR()).getQualiteNourriture();
        tv_commentaire.setText(evaluerDAO.getEvaluerByIdUAndIdR(commande.getIdU(), unResto.getIdR()).getCommentaire());
        rb_respectRecette.setRating(respectRecette);
        rb_esthetique.setRating(esthetique);
        rb_cout.setRating(cout);
        rb_qualiteN.setRating(qualiteN);
        ll_detailCOMMANDE.removeView(b_evaluation);
    }
}
