import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectoryInputComponent } from './directory-input.component';

describe('DirectoryInputComponent', () => {
  let component: DirectoryInputComponent;
  let fixture: ComponentFixture<DirectoryInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectoryInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectoryInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
