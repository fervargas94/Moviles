package com.example.fernandavargas.proyectos;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.*;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class SetAlarm extends AppCompatActivity {

    public static int hour;
    public static int minutes;
    public static String currentID;
    public static int newHour;
    public static int newMinutes;

    @Override
    @TargetApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Define variables
        final TimePicker alarmTimePicker;
        PendingIntent pendingIntent;
        AlarmManager alarmManager;


        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmTimePicker.setIs24HourView(true);
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //final Calendar calendar = Calendar.getInstance();

        //Intent
        Intent intent = getIntent();
        //Alarm's id
        currentID = intent.getStringExtra("id");
        hour = intent.getIntExtra("hour", 0);
        minutes = intent.getIntExtra("minutes", 0);
        alarmTimePicker.setHour(hour);
        alarmTimePicker.setMinute(minutes);

        //Intialize alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newHour = alarmTimePicker.getHour();
                newMinutes = alarmTimePicker.getMinute();

                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + newHour + "&minutes="+ newMinutes + "&status=on";
                new saveAlarm().execute(apiURL);

            }
        });


    }

    private class saveAlarm extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.contains("ok")){

                ParseQuery<ParseObject> query = ParseQuery.getQuery("alarms");

                // Retrieve the object by id
                query.getInBackground(currentID, new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            object.put("hour", newHour);
                            object.put("minutes", newMinutes);
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(SetAlarm.this, "Alarm set ", Toast.LENGTH_SHORT).show();
                                        //Intent intent = new Intent(SetAlarm.this, MainActivity.class);
                                        //startActivity(intent);
                                    }else{
                                        Toast.makeText(SetAlarm.this, "Error! Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }else{
                Toast.makeText(SetAlarm.this, "Error! Try again!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
