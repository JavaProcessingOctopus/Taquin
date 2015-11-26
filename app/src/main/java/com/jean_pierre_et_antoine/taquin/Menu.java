package com.jean_pierre_et_antoine.taquin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_choice);
    }

    public void callAs2(View view) {
        Intent intent = new Intent(this, TaquinActivity.class);
        intent.putExtra("colNum", 2);
        startActivity(intent);
    }

    public void callAs3(View view) {
        Intent intent = new Intent(this, TaquinActivity.class);

        intent.putExtra("colNum", 3);
        startActivity(intent);
    }

    public void callAs4(View view) {
        Intent intent = new Intent(this, TaquinActivity.class);
        intent.putExtra("colNum", 4);
        startActivity(intent);
    }
}
