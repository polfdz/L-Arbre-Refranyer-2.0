package com.eroc.larbrerefranyer.larbreRefranyer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.eroc.larbrerefranyer.R;
import com.eroc.larbrerefranyer.jocestatic.Consells;
import com.eroc.larbrerefranyer.jocestatic.MenuTradicional;
import com.eroc.larbrerefranyer.jocestatic.PartidaTradicionalVides;

/**
 * Created by Pol on 05/10/2015.
 */
public class MenuPrincipal extends Activity implements View.OnClickListener {
    Context context;
    Button bPartidaTradicional, bPunts, bBiblioteca, bDuelLinia;
    ImageButton bSettings, bInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_principal);

        //Opcions del Menu
        bPartidaTradicional = (Button) findViewById(R.id.bTradicional);
        bPunts = (Button) findViewById(R.id.bpuntuacions);
        bBiblioteca = (Button)findViewById(R.id.bbiblioteca);
        bDuelLinia = (Button)findViewById(R.id.bversus);
        bSettings = (ImageButton)findViewById(R.id.bSettings);
        bInfo = (ImageButton)findViewById(R.id.bInfo);

        bPartidaTradicional.setOnClickListener(this);
        bPunts.setOnClickListener(this);
        bBiblioteca.setOnClickListener(this);
        bDuelLinia.setOnClickListener(this);
        bSettings.setOnClickListener(this);
        bInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bTradicional:
                Intent tradicional = new Intent(MenuPrincipal.this, MenuTradicional.class);
                startActivity(tradicional);
                finish();
                break;
            case R.id.bpuntuacions:
                /*Intent punt = new Intent(MenuPrincipal.this, Consells.class);
                startActivity(punt);
                finish();
                break;*/
            case R.id.bbiblioteca:
                /*Intent biblio = new Intent(MenuPrincipal.this, Biblioteca.class);
                startActivity(biblio);
                finish();
                break;*/
            case R.id.bversus:
                /*Intent versus = new Intent(MenuPrincipal.this, MenuRegistrar.class);
                startActivity(versus);
                finish();
                break;*/
            case R.id.bSettings:
                /*Intent set = new Intent(MenuPrincipal.this, Settings.class);
                startActivity(set);
                finish();
                break;*/
            case R.id.bInfo:
                /*Intent inf = new Intent(MenuPrincipal.this, Info.class);
                startActivity(inf);
                finish();
                break;*/
        }
    }
}
