package com.captech.merlick.styles;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class StylesActivity extends AppCompatActivity {

    private static final String CURRENT_THEME_PREFS = "currentThemePrefs";
    private static final String CURRENT_THEME_PREF = "currentTheme";
    private static final String LIGHT_THEME = "LightTheme";
    private static final String DARK_THEME = "DarkTheme";
    private static final String DEFUALT_THEME = LIGHT_THEME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String currentTheme = getCurrentTheme();

        if (currentTheme == null) {
            currentTheme = DEFUALT_THEME;
        }

        setCurrentTheme(currentTheme);

        if (DARK_THEME.equals(currentTheme)) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styles);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();

        getMenuInflater().inflate(R.menu.theme_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change:
                changeTheme();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void changeTheme() {
        String theme = getCurrentTheme();
        Log.v("THEME", "Theme:" + theme);

        if (DARK_THEME.equals(theme)) {
            setTheme(LIGHT_THEME);
        } else {
            setTheme(DARK_THEME);
        }
    }

    private void setTheme(String theme) {
        setCurrentTheme(theme);

        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        overridePendingTransition(0, 0);
        finish();
        Intent intent = new Intent(this, StylesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private String getCurrentTheme() {
        SharedPreferences settings = getSharedPreferences(CURRENT_THEME_PREFS, 0);
        return settings.getString(CURRENT_THEME_PREF, null);
    }

    private void setCurrentTheme(String theme) {
        SharedPreferences settings = getSharedPreferences(CURRENT_THEME_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CURRENT_THEME_PREF, theme);
        editor.commit();
    }
}
