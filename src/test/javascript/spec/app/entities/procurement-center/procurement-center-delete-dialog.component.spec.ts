import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HaryanaAgroTestModule } from '../../../test.module';
import { ProcurementCenterDeleteDialogComponent } from 'app/entities/procurement-center/procurement-center-delete-dialog.component';
import { ProcurementCenterService } from 'app/entities/procurement-center/procurement-center.service';

describe('Component Tests', () => {
  describe('ProcurementCenter Management Delete Component', () => {
    let comp: ProcurementCenterDeleteDialogComponent;
    let fixture: ComponentFixture<ProcurementCenterDeleteDialogComponent>;
    let service: ProcurementCenterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [ProcurementCenterDeleteDialogComponent]
      })
        .overrideTemplate(ProcurementCenterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProcurementCenterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProcurementCenterService);
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
