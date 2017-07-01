package org.university.entites;


public class Lesson {

    private int code;
    private String title;


    public Lesson(int code, String title)
    {
        this.code = code;
        this.title = title;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
