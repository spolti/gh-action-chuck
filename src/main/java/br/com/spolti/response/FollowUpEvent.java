package br.com.spolti.response;

import java.util.HashMap;
import java.util.List;

public class FollowUpEvent {

    private String name;
    private HashMap<String, String> data;

    public FollowUpEvent(String name) {
        this.name = name;
    }

    public FollowUpEvent(String name, HashMap<String, String> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
