package com.directoriesapi.directoryentry;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectoryEntryComparatorTest {

    @Test
    public void getInstance() throws Exception {

        assertEquals(DirectoryEntryComparator.getInstance(), DirectoryEntryComparator.getInstance());  // check if singleton

    }

    @Test
    public void compareFileAndDirectory() throws Exception {

        DirectoryEntryComparator comparator = DirectoryEntryComparator.getInstance();

        DirectoryEntry e1 = new DirectoryEntry();
        DirectoryEntry e2 = new DirectoryEntry();

        e1.setType(DirectoryEntry.Type.FILE);
        e2.setType(DirectoryEntry.Type.DIRECTORY);

        int result = comparator.compare(e1, e2);

        assertTrue(result > 0);  // directory must be lesser than file

    }

    @Test
    public void compareFiles() throws Exception {  // different comparisons of files names

        DirectoryEntryComparator comparator = DirectoryEntryComparator.getInstance();

        DirectoryEntry e1 = new DirectoryEntry();
        DirectoryEntry e2 = new DirectoryEntry();

        e1.setType(DirectoryEntry.Type.FILE);
        e2.setType(DirectoryEntry.Type.FILE);

        e1.setName("f.txt");
        e2.setName("f.txt");

        int result = comparator.compare(e1, e2);

        assertTrue(result == 0);

        e1.setName("f.txt");
        e2.setName("F.txt");

        int result2 = comparator.compare(e1, e2);

        assertTrue(result2 == 0);

        e1.setName("f10.txt");
        e2.setName("F1.txt");

        int result3 = comparator.compare(e1, e2);

        assertTrue(result3 > 0);

        e1.setName("f78_009.txt");
        e2.setName("F78_12.txt");

        int result4 = comparator.compare(e1, e2);

        assertTrue(result4 < 0);

        e1.setName("f.txt");
        e2.setName("F1.txt");

        int result5 = comparator.compare(e1, e2);

        assertTrue(result5 < 0);

        e1.setName("f008.txt");
        e2.setName("function.txt");

        int result6 = comparator.compare(e1, e2);

        assertTrue(result6 < 0);

        e1.setName("f.abc");
        e2.setName("f.def");

        int result7 = comparator.compare(e1, e2);

        assertTrue(result7 < 0);

    }

    @Test
    public void compareDirectories() throws Exception {  // different comparisons of directories names

        DirectoryEntryComparator comparator = DirectoryEntryComparator.getInstance();

        DirectoryEntry e1 = new DirectoryEntry();
        DirectoryEntry e2 = new DirectoryEntry();

        e1.setType(DirectoryEntry.Type.DIRECTORY);
        e2.setType(DirectoryEntry.Type.DIRECTORY);

        e1.setName("f.txt");
        e2.setName("f.txt");

        int result = comparator.compare(e1, e2);

        assertTrue(result == 0);

        e1.setName("f.txt");
        e2.setName("F.txt");

        int result2 = comparator.compare(e1, e2);

        assertTrue(result2 == 0);

        e1.setName("f10.txt");
        e2.setName("F1.txt");

        int result3 = comparator.compare(e1, e2);

        assertTrue(result3 > 0);

        e1.setName("f78_009.txt");
        e2.setName("F78_12.txt");

        int result4 = comparator.compare(e1, e2);

        assertTrue(result4 < 0);

        e1.setName("f.txt");
        e2.setName("F1.txt");

        int result5 = comparator.compare(e1, e2);

        assertTrue(result5 < 0);

        e1.setName("f008.txt");
        e2.setName("function.txt");

        int result6 = comparator.compare(e1, e2);

        assertTrue(result6 < 0);

        e1.setName("f.abc");
        e2.setName("f.def");

        int result7 = comparator.compare(e1, e2);

        assertTrue(result7 < 0);

    }

}