package com.eroc.larbrerefranyer.larbreRefranyer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.eroc.larbrerefranyer.R;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {
    private Context context;
    private DatabaseModel db;
    private ParseRefranys parseRefranys;
    private List<RefranyDBObject> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        db = new DatabaseModel(context);
        parseRefranys = new ParseRefranys(context, db);

        try {
            parseRefranys.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        list = db.getAllRefranys();
        for(int i = 0; i< list.size()-1; i++){
            Log.i("DB", list.get(i).getPregunta());
        }
    }


}
