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
    private DirectoryRepository directoryRepository;

    @Autowired
    private DirectoryEntryService directoryEntryService;

    public List<Directory> getAllDirectories(){

        return (List<Directory>)this.directoryRepository.findAllByOrderByTimestampDesc();

    }

    public Directory addDirectory(Directory directory){

        directory.setTimestamp(LocalDateTime.now());

        File dir = new File(directory.getPath());
        File[] fList = dir.listFiles();

        directory.setFilesNumber(Arrays.stream(fList).filter(d -> d.isFile()).count());
        directory.setDirsNumber(Arrays.stream(fList).filter(d -> d.isDirectory()).count());

        directory.setFilesSize(Arrays.stream(fList).filter(d -> d.isFile()).mapToLong(d -> d.length()).sum());

        Directory d = this.directoryRepository.save(directory);
        this.directoryEntryService.addDirectoryEntriesForDirectory(d);

        return d;
    }

    public boolean checkDirectory(Directory directory){

        Path p = Paths.get(directory.getPath());

        return (p.isAbsolute() && Files.isDirectory(p));

    }

}
