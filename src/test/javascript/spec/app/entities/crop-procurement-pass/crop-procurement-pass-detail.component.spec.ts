import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { CropProcurementPassDetailComponent } from 'app/entities/crop-procurement-pass/crop-procurement-pass-detail.component';
import { CropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

describe('Component Tests', () => {
  describe('CropProcurementPass Management Detail Component', () => {
    let comp: CropProcurementPassDetailComponent;
    let fixture: ComponentFixture<CropProcurementPassDetailComponent>;
    const route = ({ data: of({ cropProcurementPass: new CropProcurementPass(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [CropProcurementPassDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CropProcurementPassDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CropProcurementPassDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cropProcurementPass).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
