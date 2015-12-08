package com.jean_pierre_et_antoine.taquincoquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Jean-Pierre on 24/11/2015.
 */
class TaquinImageAdapter extends BaseAdapter {
    private Context mContext;
    private int width;
    private int height;
    private Bitmap[][] bouts;
    private Bitmap[][] solved;
    private Bitmap missing;
    private Bitmap blankSq;
    private int sL; //squareLenght

    public void init(){
        Bitmap img = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sexy);

        WindowManager wMan = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wMan.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        if(width < height) {
            //width = img.getWidth();
            int ratio = width / img.getWidth();
            height = img.getHeight() * ratio;
        } else {
            int ratio = height / img.getHeight();
            height = img.getWidth() * ratio;
        }

        img = Bitmap.createScaledBitmap(img, width, height, true);


        //int width = img.getWidth();
        //int height = img.getHeight();
        bouts = new Bitmap[sL][sL];
        solved = new Bitmap[sL][sL];

        for(int i=0; i<sL; i++) {
            for (int j = 0; j < sL; j++) {
                Bitmap unBout = Bitmap.createBitmap(img, (width / sL) * i, (height / sL) * j, width/sL, height/sL);
                solved[i][j] = bouts[i][j] = unBout;
            }
        }

    }

    public void shuffle(){
        int rand;
        Bitmap tmp;
        int blankX, blankY;
        blankX = blankY = sL-1;

        for(int i = 0; i < 101; i++) {
            rand = (int) (Math.random() * 4);
            for(boolean moved = false; moved == false; /*do nothing*/) {
                if (rand == 0) {
                    if(isInGrid(blankX+1)) {    //check if selected adjacent square exists
                        //switch blank square with adjacent square
                        tmp = bouts[blankX+1][blankY];
                        bouts[blankX+1][blankY] = bouts[blankX][blankY];
                        bouts[blankX][blankY] = tmp;
                        blankX++;   //update blank square position
                        moved = true;
                    } else {
                        rand++;
                    }
                } else if (rand == 1) {
                    if(isInGrid(blankY+1)) {    //check if selected adjacent square exists
                        //switch blank square with adjacent square
                        tmp = bouts[blankX][blankY+1];
                        bouts[blankX][blankY+1] = bouts[blankX][blankY];
                        bouts[blankX][blankY] = tmp;
                        blankY++;
                        moved = true;
                    } else {
                        rand++;
                    }
                } else if (rand == 2) {
                    if(isInGrid(blankX-1)) {    //check if selected adjacent square exists
                        //switch blank square with adjacent square
                        tmp = bouts[blankX-1][blankY];
                        bouts[blankX-1][blankY] = bouts[blankX][blankY];
                        bouts[blankX][blankY] = tmp;
                        blankX--;   //update blank square position
                        moved = true;
                    } else {
                        rand++;
                    }
                } else if (rand == 3) {
                    if(isInGrid(blankY-1)) {    //check if selected adjacent square exists
                        //switch blank square with adjacent square
                        tmp = bouts[blankX][blankY-1];
                        bouts[blankX][blankY-1] = bouts[blankX][blankY];
                        bouts[blankX][blankY] = tmp;
                        blankY--;   //update blank square position
                        moved = true;
                    } else {
                        rand = 0;
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void move(int position){
        move(position%sL, position/sL);
    }

    public boolean isSolved() {
        boolean res = true;

        for(int i=0; i<sL && res; i++) {
            for (int j = 0; j < sL && res; j++) {
                if(bouts[i][j]!=solved[i][j]) res = false;
            }
        }
        return res;
    }

    public boolean isInGrid(int pos) {
        return (pos < sL) && (pos >= 0);
    }

    public boolean isInGrid(int posX, int posY) {
        return isInGrid(posX) && isInGrid(posY);
    }

    public void move(int posX, int posY){
        Bitmap tmp = null;

        //searching if the free square is adjacent while making shure we don't do and OutOfBound
        if(posX+1<sL && bouts[posX+1][posY]==blankSq) {
            tmp = bouts[posX+1][posY];
            bouts[posX+1][posY] = bouts[posX][posY];
            bouts[posX][posY] = tmp;
        } else if(posY+1<sL && bouts[posX][posY+1]==blankSq) {
            tmp = bouts[posX][posY+1];
            bouts[posX][posY+1] = bouts[posX][posY];
            bouts[posX][posY] = tmp;
        } else if(posX-1>=0 && bouts[posX-1][posY]==blankSq) {
            tmp = bouts[posX-1][posY];
            bouts[posX-1][posY] = bouts[posX][posY];
            bouts[posX][posY] = tmp;
        } else if(posY-1>=0 && bouts[posX][posY-1]==blankSq) {
            tmp = bouts[posX][posY-1];
            bouts[posX][posY-1] = bouts[posX][posY];
            bouts[posX][posY] = tmp;
        }

        notifyDataSetChanged();
    }

    public TaquinImageAdapter(Context c, int colNum) {
        mContext = c;
        this.sL = colNum;
        init();
        removeSq();
        if(isSolved()) shuffle();
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
            R.drawable.sexy
    };

    public void fill() {
        bouts[sL-1][sL-1] = missing;
    }

    public void removeSq() {
        missing = bouts[sL-1][sL-1];
        blankSq = solved[sL-1][sL-1] = bouts[sL-1][sL-1] = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
    }
}