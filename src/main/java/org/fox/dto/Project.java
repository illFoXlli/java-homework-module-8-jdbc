package org.fox.dto;

public class Project {
    private int id;
    private String name;
    private int clientId;

    public Project(int id, String name, int clientId) {
        this.id = id;
        this.name = name;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | clientId=" + clientId;
    }
}