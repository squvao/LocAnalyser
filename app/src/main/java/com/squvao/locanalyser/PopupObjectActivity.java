package com.squvao.locanalyser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PopupObjectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_object);
    }

    public void buttonCloseListener(View view) {
        finish();
    }
}
