import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { FarmerUpdateComponent } from 'app/entities/farmer/farmer-update.component';
import { FarmerService } from 'app/entities/farmer/farmer.service';
import { Farmer } from 'app/shared/model/farmer.model';

describe('Component Tests', () => {
  describe('Farmer Management Update Component', () => {
    let comp: FarmerUpdateComponent;
    let fixture: ComponentFixture<FarmerUpdateComponent>;
    let service: FarmerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [FarmerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FarmerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FarmerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FarmerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Farmer(123);
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
        const entity = new Farmer();
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
