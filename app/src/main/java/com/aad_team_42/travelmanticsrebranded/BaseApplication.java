package com.aad_team_42.travelmanticsrebranded;

import android.app.Application;

import com.aad_team_42.travelmanticsrebranded.utils.FirebaseUtils;
import com.aad_team_42.travelmanticsrebranded.utils.PreferencesUtils;
import com.google.firebase.database.FirebaseDatabase;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseUtils.initializeFirebase();
        PreferencesUtils.initPreferences(this);
    }
}