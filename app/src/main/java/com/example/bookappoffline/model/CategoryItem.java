package com.example.bookappoffline.model;

public class CategoryItem {

    int cat_img; // Change from String to int to store image resource ID
    String cat_title, cat_subTitle;
    int checkCat;

    public CategoryItem(int cat_img, String cat_title, String cat_subTitle, int checkCat) {
        this.cat_img = cat_img;
        this.cat_title = cat_title;
        this.cat_subTitle = cat_subTitle;
        this.checkCat = checkCat;
    }

    public int getCat_img() {
        return cat_img;
    }

    public void setCat_img(int cat_img) {
        this.cat_img = cat_img;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_subTitle() {
        return cat_subTitle;
    }

    public void setCat_subTitle(String cat_subTitle) {
        this.cat_subTitle = cat_subTitle;
    }

    public int getCheckCat() {
        return checkCat;
    }

    public void setCheckCat(int checkCat) {
        this.checkCat = checkCat;
    }
}
