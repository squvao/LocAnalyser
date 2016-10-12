package com.squvao.locanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initComponents();
    }

    private void initComponents() {
        initToolbar();
        initFloatingActionButton();
    }

    private void initFloatingActionButton() {
/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void buttonsListener(View view) {
        int id = view.getId(); //Опредеяем идентификатор кнопки нажатой в SettingsActivity
        switch(id){
            case R.id.content_settings_button_about_us:
                /*Intent intent = new Intent(SettingsActivity.this,AboutUsActivity.class);
                startActivity(intent);*/
                break;
            case R.id.content_settings_button_contact_author:
                ShareCompat.IntentBuilder.from(SettingsActivity.this).setType("message/rfc822")
                        .addEmailTo(getString(R.string.main_activity_navigation_item_feedback_email))
                        .setSubject(getString(R.string.app_name))
                        .setText(getString(R.string.main_activity_navigation_item_feedback_message))
                        .setChooserTitle(getString(R.string.main_activity_navigation_item_feedback_title))
                        .startChooser();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        /*int Id=item.getItemId();
        switch(Id){
            case android.R.id.home:
                finish();
        }*/
        return true;
    }

}
