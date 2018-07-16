package com.directoriesapi.directoryentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DirectoryEntryController {

    @Autowired
    private DirectoryEntryService directoryEntryService;

    @RequestMapping("/directories/{id}/entries")
    public List<DirectoryEntry> getAllDirectoryEntries(@PathVariable long id){

        return directoryEntryService.getAllDirectoryEntries(id);

    }

}
