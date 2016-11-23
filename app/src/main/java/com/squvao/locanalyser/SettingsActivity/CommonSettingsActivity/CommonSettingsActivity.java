package com.squvao.locanalyser.SettingsActivity.CommonSettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squvao.locanalyser.HelpActivity;
import com.squvao.locanalyser.R;

public class CommonSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void buttonsListener(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id){
            case R.id.content_common_settings_button_application_theme:
                intent = new Intent(CommonSettingsActivity.this, ApplicationThemeActivity.class);
                startActivity(intent);
                break;
            case R.id.content_common_settings_button_application_full_screen:
                /**/
                break;
            case R.id.content_common_settings_button_language:
                /**/
                break;
            case R.id.content_common_settings_button_help:
                intent = new Intent(CommonSettingsActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
