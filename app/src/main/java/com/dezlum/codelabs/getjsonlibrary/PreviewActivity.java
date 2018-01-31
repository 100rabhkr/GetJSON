package com.dezlum.codelabs.getjsonlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Intent intent = getIntent();
        String text = intent.getExtras().getString("text", "not");
        TextView textView = findViewById(R.id.previewtext);
        textView.setText(text);
    }
}
