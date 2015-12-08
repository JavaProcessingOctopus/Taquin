package com.jean_pierre_et_antoine.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Jean-Pierre on 24/11/2015.
 */
class TaquinImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap[][] bouts;
    private Bitmap missing;
    private int sL; //squareLenght

    public void init(){
        Bitmap img = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.android);


        int width = img.getWidth();
        int height = img.getHeight();
        bouts = new Bitmap[sL][sL];

        for(int i=0; i<sL; i++) {
            for (int j = 0; j < sL; j++) {
                Bitmap unBout = Bitmap.createBitmap(img, (width / sL) * i, (height / sL) * j, width/sL, height/sL);
                bouts[i][j] = unBout;
            }
        }
        missing = bouts[sL-1][sL-1];
        bouts[sL-1][sL-1] = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
    }

    public void shuffle(){
        for(int cycle=0; cycle < 20; cycle++){
            int i = (int) (Math.random() * sL);
            int j = (int) (Math.random() * sL);
            int k = (int) (Math.random() * sL);
            int l = (int) (Math.random() * sL);
            Bitmap tmp;
            //swap
            tmp = bouts[i][j];
            bouts[i][j] =  bouts[k][l];
            bouts[k][l] = tmp;
        }
    }

    public void move(int position){
        move(position%sL, position/sL);
    }

    public void move(int posX, int posY){
        Bitmap tmp, empty;

        if(bouts[posX+1][posY]==null) {

        } else if(bouts[posX][posY+1]==null) {

        } else if(bouts[posX-1][posY]==null) {

        } else if(bouts[posX][posY-1]==null) {

        }
    }

    public TaquinImageAdapter(Context c, int colNum) {
        mContext = c;
        this.sL = colNum;
        init();
        shuffle();
    }

    public int getCount(){
        return sL*sL;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int posX = position%sL;
        int posY = position/sL;
        return getView(posX, posY, convertView, parent);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int posX, int posY, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setPadding(8, 8, 8, 8);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(bouts[posX][posY]);
        return imageView;
    }

    public int getsL(){
        return this.sL;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.android
    };
}