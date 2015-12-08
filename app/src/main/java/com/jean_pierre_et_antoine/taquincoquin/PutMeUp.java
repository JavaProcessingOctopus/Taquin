package com.jean_pierre_et_antoine.taquincoquin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class PutMeUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_me_up);

        int colNum = getIntent().getIntExtra("colNum", 3);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(colNum);
        final TaquinImageAdapter taquinImgs = new TaquinImageAdapter(this, colNum);
        gridview.setAdapter(taquinImgs);
        gridview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                taquinImgs.move(position);
                if (taquinImgs.isSolved()) {
                    taquinImgs.fill();
                    ReplayAlert alert = new ReplayAlert(taquinImgs);
                    alert.show(getFragmentManager(), "tag");
                }
            }
        });
        // Get the message from the intent

    }
}
