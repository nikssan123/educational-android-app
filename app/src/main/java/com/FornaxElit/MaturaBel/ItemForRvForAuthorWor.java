package com.FornaxElit.MaturaBel;

public class ItemForRvForAuthorWor {

    String[] listViewItems;
    int image;
    String authorName;
    //Spinner spinner;

    public ItemForRvForAuthorWor(String[] listViewItems, int image, String authorName) {
        this.listViewItems = listViewItems;
        this.image = image;
        this.authorName = authorName;
    }

    public String[] getListViewItems() {
        return listViewItems;
    }

    public int getImage() {
        return image;
    }

    public String getAuthorName(){
        return authorName;
    }
}
