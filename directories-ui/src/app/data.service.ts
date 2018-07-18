import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Directory } from './directory';
import { DirectoryEntry } from './directory-entry';
import { Observable, throwError  } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class DataService {

  constructor(private http: HttpClient) { }

  getAllDirectories(): Observable<Directory[]>{
    return this.http.get<Directory[]>("/directories");
                    
  }

  addNewDirectory(dir: Directory): Observable<Directory>{
    return this.http.post<Directory>("/directories", dir);
  }

  getAllEntriesInDirectory(id: number): Observable<DirectoryEntry[]>{
    return this.http.get<DirectoryEntry[]>("/directories/" + id + "/entries");
  }

}
