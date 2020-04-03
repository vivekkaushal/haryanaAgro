import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProcurementCenterService } from 'app/entities/procurement-center/procurement-center.service';
import { IProcurementCenter, ProcurementCenter } from 'app/shared/model/procurement-center.model';
import { CenterType } from 'app/shared/model/enumerations/center-type.model';

describe('Service Tests', () => {
  describe('ProcurementCenter Service', () => {
    let injector: TestBed;
    let service: ProcurementCenterService;
    let httpMock: HttpTestingController;
    let elemDefault: IProcurementCenter;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProcurementCenterService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProcurementCenter(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', CenterType.Godown, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ProcurementCenter', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ProcurementCenter(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProcurementCenter', () => {
        const returnedFromService = Object.assign(
          {
            state: 'BBBBBB',
            district: 'BBBBBB',
            tehsil: 'BBBBBB',
            village: 'BBBBBB',
            centerType: 'BBBBBB',
            centerIdentificationNumber: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ProcurementCenter', () => {
        const returnedFromService = Object.assign(
          {
            state: 'BBBBBB',
            district: 'BBBBBB',
            tehsil: 'BBBBBB',
            village: 'BBBBBB',
            centerType: 'BBBBBB',
            centerIdentificationNumber: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProcurementCenter', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
