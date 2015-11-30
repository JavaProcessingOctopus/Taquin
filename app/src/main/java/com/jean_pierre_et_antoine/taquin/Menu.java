package com.jean_pierre_et_antoine.taquin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Menu extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_choice);
    }

    public void callAs2(View view) {

        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);

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
