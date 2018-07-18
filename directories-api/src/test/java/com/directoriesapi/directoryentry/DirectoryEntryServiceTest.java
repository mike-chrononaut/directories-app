package com.directoriesapi.directoryentry;

import com.directoriesapi.directory.Directory;
import com.directoriesapi.directory.DirectoryRepository;
import com.directoriesapi.directory.DirectoryService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectoryEntryServiceTest {

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private DirectoryEntryService directoryEntryService;

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private DirectoryEntryRepository directoryEntryRepository;

    @After
    public void cleanDb(){
        this.directoryEntryRepository.deleteAll();  // total cleanup
        this.directoryRepository.deleteAll();
    }

    @Test
    public void getAllDirectoryEntries() throws Exception {

        File resourcesDirectory = new File("src/test/resources");  // get base directory for tests

        Directory dir = new Directory();         // dummy directory
        dir.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test2");

        this.directoryService.addDirectory(dir); // save directory to db

        List<DirectoryEntry> entries = this.directoryEntryService.getAllDirectoryEntries(dir.getId());  // get entries from db

        DirectoryEntry e1 = entries.get(0);
        DirectoryEntry e2 = entries.get(1);

        assertEquals(e1.getName(), "test1.txt");  // must be equal to predefined values
        assertEquals(e2.getName(), "test2.txt");

        assertEquals(e1.getType().toString(), "FILE");  // must be equal to predefined values
        assertEquals(e2.getType().toString(), "FILE");


    }


}