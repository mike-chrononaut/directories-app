package com.directoriesapi.directoryentry;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectoryEntryRepository extends CrudRepository<DirectoryEntry, Long> {

    public List<DirectoryEntry> findByDirectoryId(long directoryId); // method to get all entries in specific directory

}
