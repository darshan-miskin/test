package com.gne.test.vo;

import java.util.ArrayList;

public class Result {
    boolean status=true;
    ArrayList<User> results=new ArrayList<>();

    public Result(){}

    public Result(boolean status) {
        this.status = status;
        this.results=null;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<User> getResults() {
        return results;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setResults(ArrayList<User> results) {
        this.results = results;
    }
}
