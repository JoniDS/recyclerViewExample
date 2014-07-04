package com.jds.example.recyclerviewexample.adapter;

/**
 * Created by jonids on 04/07/14.
 * Example object
 */
public class MyObject {

    private int id;
    private String url;

    public MyObject(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
