import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { ProcurementCenterUpdateComponent } from 'app/entities/procurement-center/procurement-center-update.component';
import { ProcurementCenterService } from 'app/entities/procurement-center/procurement-center.service';
import { ProcurementCenter } from 'app/shared/model/procurement-center.model';

describe('Component Tests', () => {
  describe('ProcurementCenter Management Update Component', () => {
    let comp: ProcurementCenterUpdateComponent;
    let fixture: ComponentFixture<ProcurementCenterUpdateComponent>;
    let service: ProcurementCenterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [ProcurementCenterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProcurementCenterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProcurementCenterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProcurementCenterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProcurementCenter(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProcurementCenter();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
