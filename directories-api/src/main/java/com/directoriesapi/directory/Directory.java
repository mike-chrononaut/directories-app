package com.directoriesapi.directory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Directory {    //Class for directory information

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime timestamp;  // time of insertion to db
    private String path;
    private long filesNumber = 0L;    // number of files in this directory
    private long filesSize = 0L;      // total size of all files in this directory in bytes
    private long dirsNumber = 0L;     // number of subdirectories in this directory


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
