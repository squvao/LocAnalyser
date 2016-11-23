package com.squvao.locanalyser.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.squvao.locanalyser.AboutUsActivity;
import com.squvao.locanalyser.R;
import com.squvao.locanalyser.SettingsActivity.CommonSettingsActivity.CommonSettingsActivity;

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
        Intent intent = null;
        switch(id){
            case R.id.content_settings_button_common:
                intent = new Intent(SettingsActivity.this, CommonSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.content_settings_button_notifications:
                intent = new Intent(SettingsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            case R.id.content_settings_button_account:
                intent = new Intent(SettingsActivity.this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.content_settings_button_reset:
                /*Function handler*/
                break;
            case R.id.content_settings_button_about_us:
                intent = new Intent(SettingsActivity.this,AboutUsActivity.class);
                startActivity(intent);
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
