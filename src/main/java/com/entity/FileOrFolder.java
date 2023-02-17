package com.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class FileOrFolder {
    private int id;
    private int pid;
    private String name;
    private String label;
    private String path;
    private List<FileOrFolder> children;
}
