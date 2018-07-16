package com.directoriesapi.directoryentry;

import com.directoriesapi.directory.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DirectoryEntryService {

    @Autowired
    private DirectoryEntryRepository directoryEntryRepository;

    public List<DirectoryEntry> getAllDirectoryEntries(long directoryId){

        List<DirectoryEntry> list = this.directoryEntryRepository.findByDirectoryId(directoryId);
        list.sort(DirectoryEntryComparator.getInstance());
        return list;

    }

    public void addDirectoryEntriesForDirectory(Directory directory){

        File dir = new File(directory.getPath());
        File[] fList = dir.listFiles();

        if(fList != null){

            List<DirectoryEntry> list = new LinkedList<>();

            Arrays.stream(fList).forEach(e -> {
                DirectoryEntry entry = new DirectoryEntry();

                entry.setDirectory(directory);
                entry.setSize(e.length());
                entry.setName(e.getName());

                entry.setType(e.isFile() ? DirectoryEntry.Type.FILE : DirectoryEntry.Type.DIRECTORY);

                list.add(entry);
            });

            directoryEntryRepository.saveAll(list);

        }

    }
}

final class DirectoryEntryComparator implements Comparator<DirectoryEntry>{

    private static DirectoryEntryComparator instance = null;

    private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");

    private DirectoryEntryComparator(){
    }

    public static DirectoryEntryComparator getInstance(){

        if(instance == null){

            instance = new DirectoryEntryComparator();

        }
        
        return instance;
        
    }

    @Override
    public int compare(DirectoryEntry o1, DirectoryEntry o2) {

        if(o1.getType() != o2.getType()){

            return o1.getType() == DirectoryEntry.Type.DIRECTORY ? -1 : 1;

        } else {

            Matcher m1 = PATTERN.matcher(o1.getName());
            Matcher m2 = PATTERN.matcher(o2.getName());

            while(m1.find() && m2.find()){

                int nonDigitComparison = m1.group(1).compareToIgnoreCase(m2.group(1));
                if(nonDigitComparison != 0){

                    return nonDigitComparison;

                }

                if (m1.group(2).isEmpty()){

                    return m2.group(2).isEmpty() ? 0 : -1;

                } else if (m2.group(2).isEmpty()){

                    return 1;

                }

                BigInteger n1 = new BigInteger(m1.group(2));
                BigInteger n2 = new BigInteger(m2.group(2));

                int numberComparison = n1.compareTo(n2);
                if (numberComparison != 0){

                    return numberComparison;

                }

            }

            return m1.hitEnd() && m2.hitEnd() ? 0 : (m1.hitEnd() ? -1 : 1);

        }

    }

}

