package com.directoriesapi.directory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime timestamp;
    private String path;
    private long filesNumber = 0L;
    private long filesSize = 0L;
    private long dirsNumber = 0L;


    public Directory(long id, LocalDateTime timestamp, String path, long filesNumber, long filesSize, long dirsNumber) {
        this.id = id;
        this.timestamp = timestamp;
        this.path = path;
        this.filesNumber = filesNumber;
        this.filesSize = filesSize;
        this.dirsNumber = dirsNumber;
    }

    public Directory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getFilesNumber() {
        return filesNumber;
    }

    public void setFilesNumber(long filesNumber) {
        this.filesNumber = filesNumber;
    }

    public long getFilesSize() {
        return filesSize;
    }

    public void setFilesSize(long filesSize) {
        this.filesSize = filesSize;
    }

    public long getDirsNumber() {
        return dirsNumber;
    }

    public void setDirsNumber(long dirsNumber) {
        this.dirsNumber = dirsNumber;
    }
}
