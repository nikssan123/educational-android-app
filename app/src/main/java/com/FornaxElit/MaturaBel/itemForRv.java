package com.FornaxElit.MaturaBel;

import android.widget.Button;

public class itemForRv {
     //int background;
     String authorName;
     int authorImage;
     Button button;

     public itemForRv(){

     }
    //add int background to constructors
    public itemForRv(String authorName, int authorImage) {
       // this.background = background;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    /*public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }*/


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(int authorImage) {
        this.authorImage = authorImage;
    }

    public Button getButton(){
        return  button;
    }
}
