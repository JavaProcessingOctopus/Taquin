package com.jean_pierre_et_antoine.taquincoquin;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Accueil extends AppCompatActivity {
    public final static String PSEUDO = "com.jean_pierre_et_antoine.PSEUDO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, PutMeUp.class);
        EditText editText = (EditText) findViewById(R.id.pseudo);
        String message = editText.getText().toString();
        intent.putExtra(PSEUDO, message);
    }

    public void debutant(View view) {
        Intent intent = new Intent(this, PutMeUp.class);
        EditText editText = (EditText) findViewById(R.id.pseudo);
        String message = editText.getText().toString();
        intent.putExtra(PSEUDO, message);
        intent.putExtra("colNum", 2);
        startActivity(intent);
    }

    public void normal(View view) {
        Intent intent = new Intent(this, PutMeUp.class);
        EditText editText = (EditText) findViewById(R.id.pseudo);
        String message = editText.getText().toString();
        intent.putExtra(PSEUDO, message);
        intent.putExtra("colNum", 3);
        startActivity(intent);
    }

    public void expert(View view) {
        Intent intent = new Intent(this, PutMeUp.class);
        EditText editText = (EditText) findViewById(R.id.pseudo);
        String message = editText.getText().toString();
        intent.putExtra(PSEUDO, message);
        intent.putExtra("colNum", 4);
        startActivity(intent);
    }

}
