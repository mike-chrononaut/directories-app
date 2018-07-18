import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Directory } from './directory';
import { DirectoryEntry } from './directory-entry';
import { Observable } from 'rxjs';

@Injectable()
export class DataService {

  constructor(private http: HttpClient) { }

  getAllDirectories(): Observable<Directory[]>{
    return this.http.get<Directory[]>("/directories");  // get all directories from api for initial showing
                    
  }

  addNewDirectory(dir: Directory): Observable<Directory>{
    return this.http.post<Directory>("/directories", dir);  // add new directory and return full version of it or 404 error
  }

  getAllEntriesInDirectory(id: number): Observable<DirectoryEntry[]>{
    return this.http.get<DirectoryEntry[]>("/directories/" + id + "/entries");  // get all entries from specific directory
  }

}
