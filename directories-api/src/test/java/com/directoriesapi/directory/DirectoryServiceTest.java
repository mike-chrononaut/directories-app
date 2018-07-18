package com.directoriesapi.directory;

import com.directoriesapi.directoryentry.DirectoryEntryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectoryServiceTest {

    @Autowired
    private DirectoryService directoryService;

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
    public void getAllDirectories() throws Exception {

        File resourcesDirectory = new File("src/test/resources");    // get base directory for tests

        Directory dir1 = new Directory();
        dir1.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test1"); // first dummy directory

        Directory dir2 = new Directory();
        dir2.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test2"); // second dummy directory

        List<Directory> list = new LinkedList<>();
        list.add(dir1);
        list.add(dir2);

        this.directoryService.addDirectory(dir1);  // save directories to db
        this.directoryService.addDirectory(dir2);

        List<Directory> directories = this.directoryService.getAllDirectories();
        directories.sort((e1, e2) -> {  // sort by id to provide ordering
            Long l1 = e1.getId();
            Long l2 = e2.getId();

            return l1.compareTo(l2);});

        ListIterator<Directory> i1 = list.listIterator();         // our first list
        ListIterator<Directory> i2 = directories.listIterator();  // list from db

        assertEquals(list.size(), directories.size());  // size must be equal

        while(i1.hasNext() && i1.hasNext()){

            Directory e1 = i1.next();
            Directory e2 = i2.next();

            assertEquals(e1.getPath(), e2.getPath());  // path names must be equal

        }

    }

    @Test
    public void addDirectory() throws Exception {

        Directory dir = new Directory();  // dummy directory

        File resourcesDirectory = new File("src/test/resources");    // get base directory for tests
        String path = resourcesDirectory.getAbsolutePath() + "/testDir/test2";

        dir.setPath(path);

        this.directoryService.addDirectory(dir);  // save directory to db

        Directory res = this.directoryService.getAllDirectories().get(0);

        assertEquals(res.getPath(), path);                 // main fields of directory from db must be equal to fields from dummy directory
        assertEquals(res.getDirsNumber(), 0);
        assertEquals(res.getFilesNumber(), 2);

    }

    @Test
    public void checkDirectory() throws Exception {

        File resourcesDirectory = new File("src/test/resources");  // get base directory for tests

        Directory dir = new Directory();

        dir.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test1");
        assertTrue(this.directoryService.checkDirectory(dir));  // check existing directory

        dir.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test1/test.txt");
        assertFalse(this.directoryService.checkDirectory(dir)); // check not existing directory

        dir.setPath("../");
        assertFalse(this.directoryService.checkDirectory(dir)); // check relative path

        dir.setPath(resourcesDirectory.getAbsolutePath() + "/noExist");
        assertFalse(this.directoryService.checkDirectory(dir)); // check relative and not existing path

        dir.setPath(resourcesDirectory.getAbsolutePath() + "/testDir/test1/../../");
        assertTrue(this.directoryService.checkDirectory(dir));  // check absolute path ( not canonical )

    }

}