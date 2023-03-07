package com.hb.todo.pojo;

public class Todo_POJO {
    public Todo_POJO(long id, String name, String urgency){
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }

    private long id;
    private String name;
    private String urgency;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
