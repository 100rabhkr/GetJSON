package com.dezlum.codelabs.getjsonlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.edittext);
        Button getjson = findViewById(R.id.getJSON);
        getjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().trim().equals("")) {

                    try {
                        String s = new GetJson().AsString(editText.getText().toString().trim());
                        Intent intent = new Intent(MainActivity.this, PreviewActivity.class).putExtra("text", s);
                        startActivity(intent);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "no", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button getspeed = findViewById(R.id.getSPEED);
        getspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (new GetJson().isConnected(MainActivity.this)) {
                    try {
                        double speed = new GetJson().getInternetSpeed();
                        String s = "The speed is " + speed + " Megabits per second";
                        Intent intent = new Intent(MainActivity.this, PreviewActivity.class).putExtra("text", s);
                        startActivity(intent);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "no", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
