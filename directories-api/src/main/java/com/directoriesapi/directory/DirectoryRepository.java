package com.directoriesapi.directory;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectoryRepository extends CrudRepository<Directory, Long> {

    public List<Directory> findAllByOrderByTimestampDesc();

}
