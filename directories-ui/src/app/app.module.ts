import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { DirectoryInputComponent } from './directory-input/directory-input.component';
import { TableComponent } from './table/table.component';
import { DataService } from './data.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatDialogModule,
  MatInputModule,
  MatSortModule,
  MatTableModule,
  MatTooltipModule
} from '@angular/material';
import { FileSizePipe } from './file-size.pipe';
import { EntriesComponent } from './entries/entries.component';

@NgModule({
  declarations: [
    AppComponent,
    DirectoryInputComponent,
    TableComponent,
    FileSizePipe,
    EntriesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,

    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatSortModule,
    MatTableModule,
    MatTooltipModule,

    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DataService],
  entryComponents: [EntriesComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
