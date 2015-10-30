package com.eroc.larbrerefranyer.jocestatic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Pol on 30/10/2015.
 */
public class PartidaTradicionalVides extends Activity implements PartidaTradicional, View.OnClickListener {
    Context context;
    DatabaseModel db;
    int nivell; //Pasar des de MenuTradicional
    ArrayList<Integer> refranysFets;
    List<RefranyDBObject> refranys;
    TextView tPregunta, tVides;
    Button bResposta1, bResposta2, bResposta3;
    int progres; //numero de fuller acertades
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.partida_tradicional);

        context = getApplicationContext();
        db = new DatabaseModel(context);
        refranysFets = new ArrayList<Integer>();

        //getNivell del Menu
        nivell = 1;
        setLayout();
        refranys = getRefranys(nivell);
        Log.i("DEBUGER", ""+refranys.size());
        jugarPartida();
    }
    public void setLayout(){
        tPregunta = (TextView) findViewById(R.id.pregunta);
        bResposta1 = (Button) findViewById(R.id.resposta1);
        bResposta2 = (Button) findViewById(R.id.resposta2);
        bResposta3 = (Button) findViewById(R.id.resposta3);
        tVides = (TextView) findViewById(R.id.text_vides); progres = 0;
        //test = (TextView) findViewById(R.id.test);

        bResposta1.setOnClickListener(this);
        bResposta2.setOnClickListener(this);
        bResposta3.setOnClickListener(this);

    }
    @Override
    public void jugarPartida() {
        int i = generateRandom();
        while (refranysFets.contains(i)) {
            i = generateRandom();
        }
        Log.i("DEBUGER", "" + i);
        refranysFets.add(i);
        tVides.setText(""+ progres);
        tPregunta.setText(refranys.get(i).getPregunta());
        bResposta1.setText(refranys.get(i).getResposta());
        bResposta2.setText(refranys.get(i).getAlternativa1());
        bResposta3.setText(refranys.get(i).getAlternativa2());
    }

    @Override
    public List<RefranyDBObject> getRefranys(int _nivell)
    {
        return db.getRefranysByNivell(_nivell);
    }

    public int generateRandom()
    {
        int max = 48;
        int min = 0;
        int randomNum;
        Random rand = new Random();
        return randomNum = rand.nextInt((max - min) + 1) + min;
    }

    public void tramitarFinalPartida(int _nivell){
        //intent FinalPartida!
        Intent partida = new Intent(PartidaTradicionalVides.this, MainActivity.class);
        startActivity(partida);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.resposta1:
                progres++;
                if(progres == refranys.size()){
                    tramitarFinalPartida(nivell);
                }
                jugarPartida();
                break;
        }
    }
}
