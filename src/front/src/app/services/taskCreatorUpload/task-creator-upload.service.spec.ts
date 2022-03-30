import { TestBed } from '@angular/core/testing';

import { TaskCreatorUploadService } from './task-creator-upload.service';

describe('TaskCreatorUploadService', () => {
  let service: TaskCreatorUploadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskCreatorUploadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
