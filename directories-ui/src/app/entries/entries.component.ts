import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TableComponent } from '../table/table.component';
import { Directory } from '../directory';
import { Inject } from '@angular/core';
import { DirectoryEntry } from '../directory-entry'
import { MatTable } from '@angular/material';
import { DataService } from '../data.service';
import { FileSizePipe } from '../file-size.pipe';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrls: ['./entries.component.css']
})
export class EntriesComponent implements OnInit {

  entries: DirectoryEntry[] = []; // main data source for table

  displayedColumns: string[] = ['name', 'size'];  // columns to show

  @ViewChild(MatTable) table: MatTable<any>;

  constructor(private dialogRef: MatDialogRef<TableComponent>,
              @Inject(MAT_DIALOG_DATA) public dir: Directory,
              private dataService: DataService) { }

  ngOnInit() {
    this.getAllEntries();  // populate table on initial load
  }

  getAllEntries() {
    this.dataService.getAllEntriesInDirectory(this.dir.id).subscribe(data => this.entries = data);
  }

  close() {
    this.dialogRef.close();  // close current dialog
  }

}
