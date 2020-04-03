import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HaryanaAgroTestModule } from '../../../test.module';
import { FarmerDeleteDialogComponent } from 'app/entities/farmer/farmer-delete-dialog.component';
import { FarmerService } from 'app/entities/farmer/farmer.service';

describe('Component Tests', () => {
  describe('Farmer Management Delete Component', () => {
    let comp: FarmerDeleteDialogComponent;
    let fixture: ComponentFixture<FarmerDeleteDialogComponent>;
    let service: FarmerService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [FarmerDeleteDialogComponent]
      })
        .overrideTemplate(FarmerDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FarmerDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FarmerService);
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
