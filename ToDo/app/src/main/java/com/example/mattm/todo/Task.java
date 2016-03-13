package com.example.mattm.todo;


public class Task {

    private String text;
    private boolean bool;


    public Task(String text, boolean bool) {
        this.text = text;
        this.bool = bool;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
