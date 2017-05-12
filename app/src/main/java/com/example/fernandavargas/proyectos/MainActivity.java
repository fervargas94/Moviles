package com.example.fernandavargas.proyectos;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int h1, h2, h3, h4, h5, h6;
    public int m1, m2, m3, m4, m5, m6;
    public String id1, id2, id3, id4, id5, id6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Parse
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("p8ahjoXfgrIm8Ldu7AYJ4FET1UT6z9YuE7uT596Y")
                .clientKey("2B1qmAcGE0FhcdeEmYS1XeeXgb9R1oyC6AK6rPug")
                .server("https://parseapi.back4app.com/").build());

        final TextView alarm1, alarm2, alarm3, alarm4, alarm5, alarm6;
        final Switch sw1, sw2, sw3, sw4, sw5, sw6;
        //Textviews
        alarm1 = (TextView) findViewById(R.id.alarm1); alarm2 = (TextView) findViewById(R.id.alarm2);
        alarm3 = (TextView) findViewById(R.id.alarm3); alarm4 = (TextView) findViewById(R.id.alarm4);
        alarm5 = (TextView) findViewById(R.id.alarm5); alarm6 = (TextView) findViewById(R.id.alarm6);
        //Switch
        sw1 = (Switch) findViewById(R.id.switch1); sw2 = (Switch) findViewById(R.id.switch2);
        sw3 = (Switch) findViewById(R.id.switch3); sw4 = (Switch) findViewById(R.id.switch4);
        sw5 = (Switch) findViewById(R.id.switch5); sw6 = (Switch) findViewById(R.id.switch6);

        //Select all alarms
        ParseQuery<ParseObject> query = ParseQuery.getQuery("alarms");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    for(int i = 0; i < scoreList.size(); i++){
                        ParseObject object;
                        object = scoreList.get(i);
                        String hour ;
                        String minutes;

                        //Check hours
                        if ((Integer) object.get("hour") >= 10 ){
                            hour = object.get("hour").toString();
                        }else{
                            hour = "0" + object.get("hour").toString();
                        }
                        //Check minutes
                        if ((Integer) object.get("minutes") >= 10 ){
                            minutes = object.get("minutes").toString();
                        }else{
                            minutes = "0" + object.get("minutes").toString();
                        }
                        String time = hour + ":" + minutes;
                        boolean setOn = (boolean) object.get("on");
                        switch (i){
                            case 0:
                                alarm1.setText(time);
                                h1 = Integer.parseInt(hour);
                                m1 = Integer.parseInt(minutes);
                                id1 = object.getObjectId().toString();
                                if (setOn){
                                    sw1.setChecked(true);
                                }else{
                                    sw1.setChecked(false);
                                }
                                break;
                            case 1:
                                alarm2.setText(time);
                                h2 = Integer.parseInt(hour);
                                m2 = Integer.parseInt(minutes);
                                id2 = object.getObjectId().toString();
                                if (setOn){
                                    sw2.setChecked(true);
                                }else{
                                    sw2.setChecked(false);
                                }
                                break;
                            case 2:
                                alarm3.setText(time);
                                h3 = Integer.parseInt(hour);
                                m3 = Integer.parseInt(minutes);
                                id3 = object.getObjectId().toString();
                                if (setOn){
                                    sw3.setChecked(true);
                                }else{
                                    sw3.setChecked(false);
                                }
                                break;
                            case 3:
                                alarm4.setText(time);
                                h4 = Integer.parseInt(hour);
                                m4 = Integer.parseInt(minutes);
                                id4 = object.getObjectId().toString();
                                if (setOn){
                                    sw4.setChecked(true);
                                }else{
                                    sw4.setChecked(false);
                                }
                                break;
                            case 4:
                                alarm5.setText(time);
                                h5 = Integer.parseInt(hour);
                                m5 = Integer.parseInt(minutes);
                                id5 = object.getObjectId().toString();
                                if (setOn){
                                    sw5.setChecked(true);
                                }else{
                                    sw5.setChecked(false);
                                }
                                break;
                            case 5:
                                alarm6.setText(time);
                                h6 = Integer.parseInt(hour);
                                m6 = Integer.parseInt(minutes);
                                id6 = object.getObjectId().toString();
                                if (setOn){
                                    sw6.setChecked(true);
                                }else{
                                    sw6.setChecked(false);
                                }
                                break;
                        }
                    }
                } else {
                    Log.d("score", "Failed");

                }
            }
        });

        alarm1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id1);
                intent.putExtra("hour", h1);
                intent.putExtra("minutes", m1);
                startActivity(intent);
            }
        });

        alarm2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id2);
                intent.putExtra("hour", h2);
                intent.putExtra("minutes", m2);
                startActivity(intent);
            }
        });

        alarm3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id3);
                intent.putExtra("hour", h3);
                intent.putExtra("minutes", m3);
                startActivity(intent);
            }
        });

        alarm4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id4);
                intent.putExtra("hour", h4);
                intent.putExtra("minutes", m4);
                startActivity(intent);
            }
        });

        alarm5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id5);
                intent.putExtra("hour", h5);
                intent.putExtra("minutes", m5);
                startActivity(intent);
            }
        });

        alarm6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                intent.putExtra("id", id6);
                intent.putExtra("hour", h6);
                intent.putExtra("minutes", m6);
                startActivity(intent);
            }
        });

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h1 + "&minutes="+ m1;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h1), String.valueOf(m1), id1, "on");
                } else {
                    apiURL += "&status=off";
                    new saveAlarm().execute(apiURL, String.valueOf(h1), String.valueOf(m1), id1, "off");
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h2 + "&minutes="+ m2;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h2), String.valueOf(m2), id2, "on");
                } else {
                    System.out.println("Apagarlo");
                    apiURL += "&status=off";
                    System.out.println(apiURL);
                    new saveAlarm().execute(apiURL, String.valueOf(h2), String.valueOf(m2), id2, "off");
                }
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h3 + "&minutes="+ m3;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h3), String.valueOf(m3), id3, "on");
                } else {
                    apiURL += "&status=off";
                    new saveAlarm().execute(apiURL, String.valueOf(h3), String.valueOf(m3), id3, "off");
                }
            }
        });

        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h4 + "&minutes="+ m4;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h4), String.valueOf(m4), id4, "on");
                } else {
                    apiURL += "&status=off";
                    new saveAlarm().execute(apiURL, String.valueOf(h4), String.valueOf(m4), id4, "off");
                }
            }
        });

        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h5 + "&minutes="+ m5;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h5), String.valueOf(m5), id5, "on");
                } else {
                    apiURL += "&status=off";
                    new saveAlarm().execute(apiURL, String.valueOf(h5), String.valueOf(m5), id5, "off");
                }
            }
        });

        sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                String apiURL = "http://192.168.1.78:5000/set_alarm?hour=" + h6 + "&minutes="+ m6;
                if (bChecked) {
                    apiURL += "&status=on";
                    new saveAlarm().execute(apiURL, String.valueOf(h6), String.valueOf(m6), id6, "on");
                } else {
                    apiURL += "&status=off";
                    new saveAlarm().execute(apiURL, String.valueOf(h6), String.valueOf(m6), id6, "off");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    //Send to settings view
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private class saveAlarm extends AsyncTask<String, Void, String> {

        public int newHour;
        public int newMinutes;
        public String currentID;
        public String onOff;

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                newHour = Integer.parseInt(params[1]);
                newMinutes = Integer.parseInt(params[2]);
                currentID = params[3];
                onOff = params[4];
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
            System.out.println(result);
            if(result.contains("ok")){

                ParseQuery<ParseObject> query = ParseQuery.getQuery("alarms");

                // Retrieve the object by id
                //CAMBIAR AQUI
                query.getInBackground(currentID, new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            if(onOff.contains("on")){
                                object.put("on", true);
                            }else{
                                object.put("on", false);
                            }
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null)
                                        Toast.makeText(MainActivity.this, "Alarm status changed ", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(MainActivity.this, "Error! Try again!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }else{
                Toast.makeText(MainActivity.this, "Error! Try again!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
