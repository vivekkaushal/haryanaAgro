import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HaryanaAgroTestModule } from '../../../test.module';
import { CropProcurementPassDeleteDialogComponent } from 'app/entities/crop-procurement-pass/crop-procurement-pass-delete-dialog.component';
import { CropProcurementPassService } from 'app/entities/crop-procurement-pass/crop-procurement-pass.service';

describe('Component Tests', () => {
  describe('CropProcurementPass Management Delete Component', () => {
    let comp: CropProcurementPassDeleteDialogComponent;
    let fixture: ComponentFixture<CropProcurementPassDeleteDialogComponent>;
    let service: CropProcurementPassService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [CropProcurementPassDeleteDialogComponent]
      })
        .overrideTemplate(CropProcurementPassDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CropProcurementPassDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CropProcurementPassService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
