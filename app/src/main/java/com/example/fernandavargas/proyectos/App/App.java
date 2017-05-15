package com.example.fernandavargas.proyectos.App;

/**
 * Created by fernandavargas on 12/05/17.
 */

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("p8ahjoXfgrIm8Ldu7AYJ4FET1UT6z9YuE7uT596Y")
                .clientKey("2B1qmAcGE0FhcdeEmYS1XeeXgb9R1oyC6AK6rPug")
                .server("https://parseapi.back4app.com/").build()
        );

    }
}