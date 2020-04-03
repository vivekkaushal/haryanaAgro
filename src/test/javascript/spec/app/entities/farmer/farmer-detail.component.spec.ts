import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HaryanaAgroTestModule } from '../../../test.module';
import { FarmerDetailComponent } from 'app/entities/farmer/farmer-detail.component';
import { Farmer } from 'app/shared/model/farmer.model';

describe('Component Tests', () => {
  describe('Farmer Management Detail Component', () => {
    let comp: FarmerDetailComponent;
    let fixture: ComponentFixture<FarmerDetailComponent>;
    const route = ({ data: of({ farmer: new Farmer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HaryanaAgroTestModule],
        declarations: [FarmerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FarmerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FarmerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.farmer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
