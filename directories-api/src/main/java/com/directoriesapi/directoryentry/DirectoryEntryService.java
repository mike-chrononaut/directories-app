package com.directoriesapi.directoryentry;

import com.directoriesapi.directory.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DirectoryEntryService {

    @Autowired
    private DirectoryEntryRepository directoryEntryRepository;    // CRUD repository with specific method

    public List<DirectoryEntry> getAllDirectoryEntries(long directoryId){

        List<DirectoryEntry> list = this.directoryEntryRepository.findByDirectoryId(directoryId);  // get all entries in directory
        list.sort(DirectoryEntryComparator.getInstance());                                         // use number aware sort
        return list;

    }

    public void addDirectoryEntriesForDirectory(Directory directory){  // supporting method for DirectoryService

        File dir = new File(directory.getPath());
        File[] fList = dir.listFiles();             // get inner files and subdirectories

        if(fList != null){

            List<DirectoryEntry> list = new LinkedList<>();

            Arrays.stream(fList).forEach(e -> {                 // populate list of entries in directory
                DirectoryEntry entry = new DirectoryEntry();

                entry.setDirectory(directory);
                entry.setSize(e.length());
                entry.setName(e.getName());

                entry.setType(e.isFile() ? DirectoryEntry.Type.FILE : DirectoryEntry.Type.DIRECTORY);

                list.add(entry);
            });

            directoryEntryRepository.saveAll(list);           // save list of entries to db

        }

    }
}

final class DirectoryEntryComparator implements Comparator<DirectoryEntry>{  // special number aware comparator

    private static DirectoryEntryComparator instance = null;

    private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)"); // regex for further use

    private DirectoryEntryComparator(){
    }

    public static DirectoryEntryComparator getInstance(){                  // our comparator is singleton

        if(instance == null){

            instance = new DirectoryEntryComparator();

        }
        
        return instance;
        
    }

    @Override
    public int compare(DirectoryEntry o1, DirectoryEntry o2) {

        if(o1.getType() != o2.getType()){

            return o1.getType() == DirectoryEntry.Type.DIRECTORY ? -1 : 1;  // directory is always lesser than file

        } else {

            String o1FileName = o1.getName().replaceFirst("[.][^.]+$", "");        // get file names without extension
            String o2FileName = o2.getName().replaceFirst("[.][^.]+$", "");

            String o1ExtensionName = o1.getName().substring(o1.getName().lastIndexOf(".") + 1); // get extensions of files
            String o2ExtensionName = o2.getName().substring(o2.getName().lastIndexOf(".") + 1);

            Matcher m1 = PATTERN.matcher(o1FileName);
            Matcher m2 = PATTERN.matcher(o2FileName);

            while(m1.find() && m2.find()){

                int nonDigitComparison = m1.group(1).compareToIgnoreCase(m2.group(1));  // compare non digit part of file name ignoring case
                if(nonDigitComparison != 0){

                    return nonDigitComparison;

                }

                if (m1.group(2).isEmpty()){    // check if both names have first and second groups( for example, if not, f.txt < f1.txt )

                    return m2.group(2).isEmpty() ? (o1ExtensionName.compareToIgnoreCase(o2ExtensionName)) : -1;  // if both second groups are empty compare extensions

                } else if (m2.group(2).isEmpty()){

                    return 1;

                }

                BigInteger n1 = new BigInteger(m1.group(2));
                BigInteger n2 = new BigInteger(m2.group(2));

                int numberComparison = n1.compareTo(n2);  // compare digit part of file name
                if (numberComparison != 0){

                    return numberComparison;

                }

            }

            return m1.hitEnd() && m2.hitEnd() ? (o1ExtensionName.compareToIgnoreCase(o2ExtensionName)) : (m1.hitEnd() ? -1 : 1);  // in all other cases

        }

    }

}

