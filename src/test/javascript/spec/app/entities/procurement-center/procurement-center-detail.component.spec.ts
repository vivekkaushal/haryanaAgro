import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { ProcurementCenterDetailComponent } from 'app/entities/procurement-center/procurement-center-detail.component';
import { ProcurementCenter } from 'app/shared/model/procurement-center.model';

describe('Component Tests', () => {
  describe('ProcurementCenter Management Detail Component', () => {
    let comp: ProcurementCenterDetailComponent;
    let fixture: ComponentFixture<ProcurementCenterDetailComponent>;
    const route = ({ data: of({ procurementCenter: new ProcurementCenter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [ProcurementCenterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProcurementCenterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProcurementCenterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.procurementCenter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
