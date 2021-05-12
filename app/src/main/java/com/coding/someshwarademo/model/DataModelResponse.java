package com.coding.someshwarademo.model;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import java.util.ArrayList;

public class DataModelResponse {

    private Integer count;
    private String next,previous;
    private ArrayList<DataModel> results;

    public DataModelResponse(Integer count, String next, String previous, ArrayList<DataModel> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<DataModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<DataModel> results) {
        this.results = results;
    }
}
