package io.github.farhadfaghihi;

import android.graphics.Color;

/**
 * Created by farhad on 1/18/17.
 */

public class ColorData {

    private int color ;
    private int textColor ;
    private String title ;

    public ColorData(){

    }

    public ColorData(int color,int textColor,String title){

        this.color = color ;
        this.textColor = textColor ;
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

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
