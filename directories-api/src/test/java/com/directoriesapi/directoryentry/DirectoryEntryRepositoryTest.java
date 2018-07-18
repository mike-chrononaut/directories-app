package com.directoriesapi.directoryentry;

import com.directoriesapi.directory.Directory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DirectoryEntryRepositoryTest{

    @Autowired
    private TestEntityManager entityManager;  // entity manager to test db operations

    @Autowired
    private DirectoryEntryRepository directoryEntryRepository;

    @Test
    public void findByDirectoryId() throws Exception {

        Directory directory = new Directory();  // dummy directory
        directory.setDirsNumber(1L);
        directory.setFilesNumber(2L);
        directory.setFilesSize(1234L);
        directory.setPath("C:/Documents");
        directory.setTimestamp(LocalDateTime.now());

        entityManager.persist(directory);  // save to db
        entityManager.flush();

        DirectoryEntry directoryEntry1 = new DirectoryEntry();  // first dummy entry
        directoryEntry1.setType(DirectoryEntry.Type.DIRECTORY);
        directoryEntry1.setName("C:/Documents/test1");
        directoryEntry1.setSize(12345L);
        directoryEntry1.setDirectory(directory);

        DirectoryEntry directoryEntry2 = new DirectoryEntry(); // second dummy entry
        directoryEntry2.setType(DirectoryEntry.Type.FILE);
        directoryEntry2.setName("C:/Documents/test.txt");
        directoryEntry2.setSize(123L);
        directoryEntry2.setDirectory(directory);


        List<DirectoryEntry> list = new LinkedList<>();
        list.add(directoryEntry1);
        list.add(directoryEntry2);

        entityManager.persist(directoryEntry1);  // save entries to db
        entityManager.flush();
        entityManager.persist(directoryEntry2);
        entityManager.flush();

        List<DirectoryEntry> entryList = directoryEntryRepository.findByDirectoryId(directory.getId());
        entryList.sort((e1, e2) -> {  // sort by id to provide ordering
            Long l1 = e1.getId();
            Long l2 = e2.getId();

            return l1.compareTo(l2);});


        assertEquals(list.size(), entryList.size());  // size must be equal

        ListIterator<DirectoryEntry> i1 = list.listIterator();      // our first list
        ListIterator<DirectoryEntry> i2 = entryList.listIterator(); // list from db

        while(i1.hasNext() && i1.hasNext()){

            DirectoryEntry e1 = i1.next();
            DirectoryEntry e2 = i2.next();

            assertEquals(e1.getType(), e2.getType());  // main fields of entry from db must be equal to fields from dummy entry
            assertEquals(e1.getName(), e2.getName());
            assertEquals(e1.getSize(), e2.getSize());

        }

    }

}