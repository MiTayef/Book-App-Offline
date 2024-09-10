package com.example.bookappoffline.model;

public class BookItem {

    private String title;
    private String subTitle;
    private String pdfFileName; // Field to store the PDF file name

    // Constructor
    public BookItem(String title, String subTitle, String pdfFileName) {
        this.title = title;
        this.subTitle = subTitle;
        this.pdfFileName = pdfFileName;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for subTitle
    public String getSubTitle() {
        return subTitle;
    }

    // Getter for pdfFileName
    public String getPdfFileName() {
        return pdfFileName; // Return the PDF file name
    }
}
