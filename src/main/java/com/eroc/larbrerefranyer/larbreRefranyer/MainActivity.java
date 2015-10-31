package com.eroc.larbrerefranyer.larbreRefranyer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eroc.larbrerefranyer.R;
import com.eroc.larbrerefranyer.jocestatic.MenuTradicional;
import com.eroc.larbrerefranyer.jocestatic.PartidaTradicionalVides;
import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.DatabaseModel;
import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.ParseRefranys;
import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.RefranyDBObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private Context context;
    private DatabaseModel db;
    private ParseRefranys parseRefranys;
    private List<RefranyDBObject> list;

    private ProgressBar pBar;
    private TextView tText;
    private Button bPartida;
    int lastId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        db = new DatabaseModel(context);
        parseRefranys = new ParseRefranys(context, db);

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        tText = (TextView) findViewById(R.id.textView);
        bPartida = (Button) findViewById(R.id.bPartidaTradicional);
        bPartida.setOnClickListener(this);

        list = db.getAllRefranys();

        if(list.size() == 0) {
            try {
                parseRefranys.parse();
                lastId = db.getLastID();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            lastId = db.getLastID();
            if(lastId != list.size()){
                try {
                    parseRefranys.parse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        pBar.setVisibility(View.GONE);
        tText.setVisibility(View.VISIBLE);
        //tText.setText(+list.size()+" Refranys afegits a la BBDD");
        tText.setText(+lastId+": Last ID. "+ list.size()+": Refranys a la BBDD");
        /*for(int i = 0; i< list.size()-1; i++){
            Log.i("DB", list.get(i).getPregunta());
        }*/
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bPartidaTradicional:
                Intent partida = new Intent(MainActivity.this, MenuTradicional.class);
                startActivity(partida);
                break;
        }
    }
}
