package com.eroc.larbrerefranyer.jocestatic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.eroc.larbrerefranyer.R;

/**
 * Created by Pol on 05/10/2015.
 */
public class MenuTradicional extends Activity implements View.OnClickListener{
    Context context;

    View vPrimer, vSegon, vTercer, vQuart, vCinque, vSise, vSete, vVuite, vNove; //views as buttons for levels
    View vBloq2, vBloq3, vBloq4, vBloq5, vBloq6, vBloq7, vBloq8, vBloq9; //Views of bloquings
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_tradicional);
        context = getApplicationContext();

        vPrimer = (View) findViewById(R.id.primer);
        vSegon = (View) findViewById(R.id.segon);
        vTercer = (View) findViewById(R.id.tercer);
        vQuart = (View) findViewById(R.id.cuart);
        vCinque = (View) findViewById(R.id.cinque);
        vSise = (View) findViewById(R.id.sise);
        vSete = (View) findViewById(R.id.sete);
        vVuite = (View) findViewById(R.id.vuite);
        vNove = (View) findViewById(R.id.nove);

        vPrimer.setOnClickListener(this);
        vSegon.setOnClickListener(this);
        vTercer.setOnClickListener(this);
        vQuart.setOnClickListener(this);
        vCinque.setOnClickListener(this);
        vSise.setOnClickListener(this);
        vSete.setOnClickListener(this);
        vVuite.setOnClickListener(this);
        vNove.setOnClickListener(this);

        vBloq2 = (View) findViewById(R.id.primerB);
        vBloq3 = (View) findViewById(R.id.tercerBlock);
        vBloq4 = (View) findViewById(R.id.cuartBlock);
        vBloq5 = (View) findViewById(R.id.cinqueBlock);
        vBloq6 = (View) findViewById(R.id.siseBlock);
        vBloq7 = (View) findViewById(R.id.seteBlock);
        vBloq8 = (View) findViewById(R.id.vuiteBlock);
        vBloq9 = (View) findViewById(R.id.noveBlock);

        pref = getSharedPreferences("Nivells", 0);
        controlarBloquejats(pref);

    }

    public void controlarBloquejats(SharedPreferences _pref)
    {
        String lvl;
        if(!(lvl = _pref.getString("lvl1","")).isEmpty()){
            vBloq2.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl2","")).isEmpty()){
            vBloq3.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl3","")).isEmpty()){
            vBloq4.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl4","")).isEmpty()){
            vBloq5.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl5","")).isEmpty()){
            vBloq6.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl6","")).isEmpty()){
            vBloq7.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl7","")).isEmpty()){
            vBloq8.setVisibility(View.GONE);
        }
        if(!(lvl = _pref.getString("lvl8","")).isEmpty()){
            vBloq9.setVisibility(View.GONE);
        }

    }
    @Override
    public void onClick(View v) {
        Intent partida;
        String lvl;
        switch(v.getId()) {
            case R.id.bDia:
                /*Intent refDia = new Intent(SeleccioNivells.this, RefranyDia.class);
                startActivity(refDia);
                finish();*/
                break;
            case R.id.bBack:
                /*Intent back = new Intent(SeleccioNivells.this, MenuPrincipal.class);
                startActivity(back);
                finish();*/
                break;
            case R.id.layout_fullesUsr:
                /*Intent perf = new Intent(SeleccioNivells.this, Perfil.class);
                startActivity(perf);
                finish();*/
                break;
            case R.id.primer:
                partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                partida.putExtra("Nivell", 1);
                startActivity(partida);
                finish();
                break;
            case R.id.segon:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl1", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 2);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 2 Bloquejat");
                }
                break;
            case R.id.tercer:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl2", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 3);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 3 Bloquejat");
                }
                break;
            case R.id.cuart:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl3", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 4);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 4 Bloquejat");
                }
                break;
            case R.id.cinque:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl4", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 5);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 5 Bloquejat");
                }
                break;
            case R.id.sise:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl5", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 6);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 6 Bloquejat");
                }
                break;
            case R.id.sete:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl6", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 7);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 7 Bloquejat");
                }
                break;
            case R.id.vuite:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl7", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 8);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 8 Bloquejat");
                }
                break;
            case R.id.nove:
                pref = getSharedPreferences("Nivells", 0);
                lvl = pref.getString("lvl8", "");
                if(!lvl.isEmpty()){
                    partida = new Intent(MenuTradicional.this, PartidaTradicionalVides.class);
                    partida.putExtra("Nivell", 9);
                    startActivity(partida);
                    finish();
                }else{
                    popUpToast("Nivell 9 Bloquejat");
                }
                break;
        }
    }
    private void popUpToast(String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
