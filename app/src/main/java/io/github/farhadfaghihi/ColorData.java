package io.github.farhadfaghihi;

import android.graphics.Color;

/**
 * Created by farhad on 1/18/17.
 */

public class ColorData {

    private int color ;
    private String title ;

    public ColorData(int color,String title){

        this.color = color ;
        this.title = title ;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
