package com.directoriesapi.directory;

import com.directoriesapi.directoryentry.DirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;      // simple CRUD repository

    @Autowired
    private DirectoryEntryService directoryEntryService;  // supporting entry service to add files and subdirectories to db

    public List<Directory> getAllDirectories(){

        return (List<Directory>)this.directoryRepository.findAll();

    }

    public Directory addDirectory(Directory directory){

        directory.setTimestamp(LocalDateTime.now());    // get date time with local timezone

        File dir = new File(directory.getPath());
        File[] fList = dir.listFiles();                 // get inner files and subdirectories; cannot be null because of check in checkDirectory method

        directory.setFilesNumber(Arrays.stream(fList).filter(d -> d.isFile()).count());
        directory.setDirsNumber(Arrays.stream(fList).filter(d -> d.isDirectory()).count());
        directory.setFilesSize(Arrays.stream(fList).filter(d -> d.isFile()).mapToLong(d -> d.length()).sum());

        Directory d = this.directoryRepository.save(directory);         // save directory to db
        this.directoryEntryService.addDirectoryEntriesForDirectory(d);  // save directory entries to db

        return d;    // return saved directory
    }

    public boolean checkDirectory(Directory directory){    // method to check directory path for correctness

        Path p = Paths.get(directory.getPath());           // get directory path

        return (p.isAbsolute() && Files.isDirectory(p));   // check if directory path is not relative (not like ../), exists
                                                           // and is not file path

    }

}
