package com.directoriesapi.directoryentry;

import com.directoriesapi.directory.Directory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class DirectoryEntry {

    public enum Type {
        DIRECTORY, FILE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String name;
    private long size = 0L;
    @ManyToOne
    @JsonIgnore
    private Directory directory;

    public DirectoryEntry(long id, Type type, String name, long size, Directory directory) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.size = size;
        this.directory = directory;
    }

    public DirectoryEntry() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

}
