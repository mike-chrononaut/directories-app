import { Component, OnInit, ViewChild } from '@angular/core';
import { Directory } from '../directory';
import { DataService } from '../data.service';
import { MatTable } from '@angular/material';
import { DatePipe } from '@angular/common';
import { FileSizePipe } from '../file-size.pipe';
import { MatSort, MatTableDataSource } from '@angular/material';
import { MatDialog } from '@angular/material';
import { EntriesComponent } from '../entries/entries.component';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  directories = new MatTableDataSource([]);  // main sortable data source for table

  displayedColumns: string[] = ['timestamp', 'path', 'dirsNumber', 'filesNumber', 'filesSize', 'button'];  // columns to show

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<any>;

  constructor(private dataService: DataService, private dialog: MatDialog) { }

  ngOnInit() {
    this.directories.sort = this.sort;
    this.getAllDirectories();
  }

  getAllDirectories() {
    this.dataService.getAllDirectories().subscribe(data => {this.directories.data = data});
  }

  addDirectory(d: Directory) {

    const data = this.directories.data;
    data.push(d);
    this.directories.data = data;

    this.table.renderRows();  // redraw our table to show added directory

  }

  showDirectoryEntries(d: Directory) {

    this.dialog.open(EntriesComponent, {  // open new dialog
      data: d
    });

  }

}
