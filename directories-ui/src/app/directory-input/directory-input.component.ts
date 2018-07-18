import { Component,ViewChild, AfterViewInit, Output, EventEmitter } from '@angular/core';
import { DataService } from '../data.service';
import { Directory } from '../directory';
import { MatTooltip } from '@angular/material';

@Component({
  selector: 'directory-input',
  templateUrl: './directory-input.component.html',
  styleUrls: ['./directory-input.component.css']
})
export class DirectoryInputComponent implements AfterViewInit {

  errorMessage: string = "Dummy error";  // tooltip message

  @ViewChild('tooltip') tooltip:MatTooltip;  // tooltip below input

  @Output() addEvent: EventEmitter<Directory> = new EventEmitter<Directory>();  // event to add new directory

  constructor(private dataService: DataService) { }

  addNewDirectory(path: string) {

    let dir: Directory = new Directory();
    dir.path = path;
    
    this.dataService.addNewDirectory(dir).subscribe(data =>  {this.addEvent.emit(data);this.disableTooltip()},
                                                    error => {this.errorMessage = error.error.message;
                                                              this.tooltip.disabled = false;
                                                              this.tooltip.show()});
  }

  disableTooltip() {
    this.tooltip.disabled = true;
  }

  ngAfterViewInit() {

    this.disableTooltip(); // on start no tooltip message

  }

}
