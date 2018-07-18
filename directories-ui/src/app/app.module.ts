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
  //MatAutocompleteModule,
  //MatBadgeModule,
  //MatBottomSheetModule,
  MatButtonModule,
  //MatButtonToggleModule,
  //MatCardModule,
  //MatCheckboxModule,
  //MatChipsModule,
  //MatDatepickerModule,
  MatDialogModule,
  //MatDividerModule,
  //MatExpansionModule,
  //MatGridListModule,
  //MatIconModule,
  MatInputModule,
  //MatListModule,
  //MatMenuModule,
  //MatNativeDateModule,
  //MatPaginatorModule,
  //MatProgressBarModule,
  //MatProgressSpinnerModule,
  //MatRadioModule,
  //MatRippleModule,
  //MatSelectModule,
  //MatSidenavModule,
  //MatSliderModule,
  //MatSlideToggleModule,
  //MatSnackBarModule,
  MatSortModule,
  //MatStepperModule,
  MatTableModule,
  //MatTabsModule,
  //MatToolbarModule,
  MatTooltipModule,
  //MatTreeModule,
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

    //MatAutocompleteModule,
    //MatBadgeModule,
    //MatBottomSheetModule,
    MatButtonModule,
    //MatButtonToggleModule,
    //MatCardModule,
    //MatCheckboxModule,
    //MatChipsModule,
    //MatStepperModule,
    //MatDatepickerModule,
    MatDialogModule,
    //MatDividerModule,
    //MatExpansionModule,
    //MatGridListModule,
    //MatIconModule,
    MatInputModule,
    //MatListModule,
    //MatMenuModule,
    //MatNativeDateModule,
    //MatPaginatorModule,
    //MatProgressBarModule,
    //MatProgressSpinnerModule,
    //MatRadioModule,
    //MatRippleModule,
    //MatSelectModule,
    //MatSidenavModule,
    //MatSliderModule,
    //MatSlideToggleModule,
    //MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    //MatTabsModule,
    //MatToolbarModule,
    MatTooltipModule,
    //MatTreeModule,

    
    BrowserAnimationsModule,
    FormsModule,
    //MatNativeDateModule,
    ReactiveFormsModule
  ],
  providers: [DataService],
  entryComponents: [EntriesComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
