package com.entity;

import java.util.List;

public class FileOrFolder {
    private int id;
    private int pid;
    private String name;
    private String label;
    private String path;
    private List<FileOrFolder> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FileOrFolder> getChildren() {
        return children;
    }

    public void setChildren(List<FileOrFolder> children) {
        this.children = children;
    }
}
