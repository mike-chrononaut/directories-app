package com.directoriesapi.directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping("/directories")
    public List<Directory> getAllDirectories(){

        return directoryService.getAllDirectories();

    }

    @RequestMapping(method= RequestMethod.POST, value="/directories")
    public Directory addDirectory(@RequestBody Directory directory){

        if(directoryService.checkDirectory(directory)){       // check directory path for correctness

            return directoryService.addDirectory(directory);

        } else {

            throw new DirectoryNotFoundException();          // throw 404 Error ( Cannot find this directory )

        }

    }

}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Не удалось найти данную директорию")
class DirectoryNotFoundException extends RuntimeException{

    public DirectoryNotFoundException(){
    }

    public DirectoryNotFoundException(String message){
        super(message);
    }

}
