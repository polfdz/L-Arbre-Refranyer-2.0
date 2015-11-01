package com.eroc.larbrerefranyer.jocestatic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.eroc.larbrerefranyer.R;

/**
 * Created by Pol on 05/10/2015.
 */
public class Consells extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.consells);
    }
}
