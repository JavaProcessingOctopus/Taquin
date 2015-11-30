package com.jean_pierre_et_antoine.taquin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class TaquinActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taquin);

        int colNum = getIntent().getIntExtra("colNum", 3);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(colNum);
        final TaquinImageAdapter taquinImgs = new TaquinImageAdapter(this, colNum);
        gridview.setAdapter(taquinImgs);
        gridview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                taquinImgs.move(position);
                if(taquinImgs.isSolved()) {
                    taquinImgs.fill();
                    ReplayAlert alert = new ReplayAlert(taquinImgs);
                    alert.show(getFragmentManager(), "tag");
                }
            }
        });
    }
}
