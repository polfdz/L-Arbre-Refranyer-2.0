package com.eroc.larbrerefranyer.jocestatic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eroc.larbrerefranyer.R;
import com.eroc.larbrerefranyer.larbreRefranyer.MainActivity;
import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.DatabaseModel;
import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.RefranyDBObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Pol on 30/10/2015.
 */
public class PartidaTradicionalVides extends Activity implements PartidaTradicional, View.OnClickListener {
    Context context;
    DatabaseModel db;
    int nivell, vides; //Pasar des de MenuTradicional
    ArrayList<Integer> refranysFets;
    List<RefranyDBObject> refranys;

    TextView tPregunta, tVides, tPunts;
    Button bResposta1, bResposta2, bResposta3;
    View vArbreEvolucio,vLayoutAcert;

    SharedPreferences sPunts; //numero de fulles

    int progres, refranyActual, puntsUsuari; //numero de fuller acertades
    String solucio; //solucio del refrany actual
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.partida_tradicional);

        context = getApplicationContext();
        db = new DatabaseModel(context);
        refranysFets = new ArrayList<Integer>();

        //getNivell del Menu
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nivell = extras.getInt("Nivell");
        }else{
            nivell = 1;
        }
        vides = 3;

        setLayout();

        refranys = getRefranys(nivell);
        Log.i("DEBUGER", "" + refranys.size());
        jugarPartida();
    }
    @Override
    public void setLayout(){
        vArbreEvolucio = (View) findViewById(R.id.arbreEvolucio);
        vLayoutAcert = (View) findViewById(R.id.arbreAcert);

        tPregunta = (TextView) findViewById(R.id.pregunta);
        bResposta1 = (Button) findViewById(R.id.resposta1);
        bResposta2 = (Button) findViewById(R.id.resposta2);
        bResposta3 = (Button) findViewById(R.id.resposta3);
        tVides = (TextView) findViewById(R.id.text_vides); progres = 0;
        tPunts = (TextView) findViewById(R.id.punts);

        bResposta1.setOnClickListener(this);
        bResposta2.setOnClickListener(this);
        bResposta3.setOnClickListener(this);

        sPunts = getSharedPreferences("Punts", 0);
        puntsUsuari = sPunts.getInt("Punts", 0);
        tPunts.setText("" + puntsUsuari);

    }
    @Override
    public void jugarPartida() {

        refranyActual = generateRandom(48,0);
        while (refranysFets.contains(refranyActual)) {
            refranyActual = generateRandom(48,0);
        }
        //Log.i("DEBUGER", "" + i);

        //random alternatives
        int perm = generateRandom(5,0);
        String alternativa1 = null;
        String alternativa2 = null;
        switch (perm){
            case 0:
                alternativa1 = refranys.get(refranyActual).getAlternativa1();
                alternativa2 = refranys.get(refranyActual).getAlternativa2();
                break;
            case 1:
                alternativa1 = refranys.get(refranyActual).getAlternativa1();
                alternativa2 = refranys.get(refranyActual).getAlternativa3();
                break;
            case 2:
                alternativa1 = refranys.get(refranyActual).getAlternativa1();
                alternativa2 = refranys.get(refranyActual).getAlternativa4();
                break;
            case 3:
                alternativa1 = refranys.get(refranyActual).getAlternativa2();
                alternativa2 = refranys.get(refranyActual).getAlternativa3();
                break;
            case 4:
                alternativa1 = refranys.get(refranyActual).getAlternativa2();
                alternativa2 = refranys.get(refranyActual).getAlternativa4();
                break;
            case 5:
                alternativa1 = refranys.get(refranyActual).getAlternativa3();
                alternativa2 = refranys.get(refranyActual).getAlternativa4();
                break;
        }

        //random posicions
        //perm = generateRandom(4,0);
        perm = 0;
        tPregunta.setText(refranys.get(refranyActual).getPregunta().toUpperCase() + "...");
        solucio = refranys.get(refranyActual).getResposta();
        switch (perm){
            case 0:
                bResposta1.setText(refranys.get(refranyActual).getResposta());
                bResposta2.setText(""+alternativa1);
                bResposta3.setText(""+alternativa2);
                break;
            case 1:
                bResposta1.setText(""+alternativa1);
                bResposta2.setText(refranys.get(refranyActual).getResposta());
                bResposta3.setText(""+alternativa2);
                break;
            case 2:
                bResposta1.setText(""+alternativa2);
                bResposta2.setText(refranys.get(refranyActual).getResposta());
                bResposta3.setText(""+alternativa1);
                break;
            case 3:
                bResposta1.setText(""+alternativa1);
                bResposta2.setText(""+alternativa2);
                bResposta3.setText(refranys.get(refranyActual).getResposta());
                break;
            case 4:
                bResposta1.setText(""+alternativa2);
                bResposta2.setText(""+alternativa1);
                bResposta3.setText(refranys.get(refranyActual).getResposta());
                break;
        }
    }

    @Override
    public List<RefranyDBObject> getRefranys(int _nivell)
    {
        return db.getRefranysByNivell(_nivell);
    }

    public int generateRandom(int _max, int _min)
    {
        int randomNum;
        Random rand = new Random();
        return randomNum = rand.nextInt((_max - _min) + 1) + _min;
    }
    @Override
    public void tramitarFinalPartida(int _nivell, boolean _superat){
        if(_superat == true) //--> nivell superat
        {
            SharedPreferences prefs = getSharedPreferences("Nivells", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("lvl"+nivell, "1");
            editor.commit();
            Intent fi = new Intent (PartidaTradicionalVides.this, MenuTradicional.class);
            startActivity(fi);
            //popUpToast("FELICITATS, NIVELL ACABAT!");
            finish();
        }else{
            //intent FinalPartida!
            Intent partida = new Intent(PartidaTradicionalVides.this, MainActivity.class);
            startActivity(partida);
        }


    }
    @Override
    public void comprovarSolucio(String _boto){
        switch (_boto){
            case "resposta1":
                if(bResposta1.getText().equals(solucio)){
                    tramitarAcert();
                }else{
                    tramitarError();
                }
                break;
            case "resposta2":
                if(bResposta2.getText().equals(solucio)){
                    tramitarAcert();
                }else{
                    tramitarError();
                }
                break;
            case "resposta3":
                if(bResposta3.getText().equals(solucio)){
                    tramitarAcert();
                }else{
                    tramitarError();
                }
                break;
        }
    }
    @Override
    public void tramitarAcert() {

        refranysFets.add(refranyActual);
        progres++;

        //actualitzar layout
        Drawable drawable = getResources().getDrawable(getResources()
                .getIdentifier("nivell1_fulla"+refranysFets.size(), "drawable", getPackageName()));
        vArbreEvolucio.setBackgroundDrawable(drawable);

        vLayoutAcert.setBackgroundResource(R.drawable.nivell1_encert);
        sumarPunts();
        if(refranysFets.size() == refranys.size()){
            tramitarFinalPartida(nivell, true); //nivell superat = true
        }else {
            jugarPartida();
        }

    }

    @Override
    public void tramitarError(){
        vides--;
        tVides.setText("" + vides);
        restarPunts();
        vLayoutAcert.setBackgroundResource(R.drawable.nivell1_error);
        if(vides == 0){
            tramitarFinalPartida(nivell, false); //nivell superat = false
        }
        jugarPartida();
    }

    @Override
    public void sumarPunts(){
        sPunts = getSharedPreferences("Punts", 0);
        SharedPreferences.Editor editor = sPunts.edit();
        puntsUsuari = sPunts.getInt("Punts", 0);
        switch(nivell){
            case 1:
                puntsUsuari++;
                break;
            case 2:
                puntsUsuari += 2;
                break;
        }
        tPunts.setText("" + puntsUsuari);
        editor.putInt("Punts", puntsUsuari);
        editor.commit();
    }
    @Override
    public void restarPunts(){
        sPunts = getSharedPreferences("Punts", 0);
        SharedPreferences.Editor editor = sPunts.edit();
        puntsUsuari = sPunts.getInt("Punts", 0);
        switch(nivell){
            case 1:
                puntsUsuari--;
                break;
            case 2:
                puntsUsuari -= 2;
                break;
        }
        tPunts.setText("" + puntsUsuari);
        editor.putInt("Punts", puntsUsuari);
        editor.commit();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.resposta1:
                comprovarSolucio("resposta1");
                break;
            case R.id.resposta2:
                comprovarSolucio("resposta2");
                break;
            case R.id.resposta3:
                comprovarSolucio("resposta3");
                break;
        }
    }
    public void onBackPressed(){
        Intent back = new Intent(this,MenuTradicional.class);
        startActivity(back);
        finish();
    }
}
