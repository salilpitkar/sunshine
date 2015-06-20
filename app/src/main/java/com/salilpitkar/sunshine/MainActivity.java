package com.salilpitkar.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean success = false;
        int id = item.getItemId();

        switch(id) {
            case R.id.action_map:
                String loc = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.loc_pref_key), getString(R.string.loc_pref_def));

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + loc));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    success = true;
                }
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                success = true;
                break;
        }

        if(!success) {
            success = super.onOptionsItemSelected(item);
        }

        return success;
    }
}
