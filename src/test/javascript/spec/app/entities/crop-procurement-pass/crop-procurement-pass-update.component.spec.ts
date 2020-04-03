import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { CropProcurementPassUpdateComponent } from 'app/entities/crop-procurement-pass/crop-procurement-pass-update.component';
import { CropProcurementPassService } from 'app/entities/crop-procurement-pass/crop-procurement-pass.service';
import { CropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

describe('Component Tests', () => {
  describe('CropProcurementPass Management Update Component', () => {
    let comp: CropProcurementPassUpdateComponent;
    let fixture: ComponentFixture<CropProcurementPassUpdateComponent>;
    let service: CropProcurementPassService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [CropProcurementPassUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CropProcurementPassUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CropProcurementPassUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CropProcurementPassService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CropProcurementPass(123);
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
        const entity = new CropProcurementPass();
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
