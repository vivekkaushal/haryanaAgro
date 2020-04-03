import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CropProcurementPassService } from 'app/entities/crop-procurement-pass/crop-procurement-pass.service';
import { ICropProcurementPass, CropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';
import { CropType } from 'app/shared/model/enumerations/crop-type.model';
import { ExpectedYieldUnit } from 'app/shared/model/enumerations/expected-yield-unit.model';
import { PassStatus } from 'app/shared/model/enumerations/pass-status.model';

describe('Service Tests', () => {
  describe('CropProcurementPass Service', () => {
    let injector: TestBed;
    let service: CropProcurementPassService;
    let httpMock: HttpTestingController;
    let elemDefault: ICropProcurementPass;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CropProcurementPassService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CropProcurementPass(
        0,
        currentDate,
        CropType.Paddy,
        'AAAAAAA',
        ExpectedYieldUnit.Bag,
        currentDate,
        PassStatus.Approved,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            cropDate: currentDate.format(DATE_TIME_FORMAT),
            procurementDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a CropProcurementPass', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            cropDate: currentDate.format(DATE_TIME_FORMAT),
            procurementDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            cropDate: currentDate,
            procurementDate: currentDate,
            createdAt: currentDate,
            modifiedAt: currentDate
          },
          returnedFromService
        );
        service
          .create(new CropProcurementPass(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a CropProcurementPass', () => {
        const returnedFromService = Object.assign(
          {
            cropDate: currentDate.format(DATE_TIME_FORMAT),
            cropType: 'BBBBBB',
            expectedYeild: 'BBBBBB',
            expectedYeildUnits: 'BBBBBB',
            procurementDate: currentDate.format(DATE_TIME_FORMAT),
            passStatus: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cropDate: currentDate,
            procurementDate: currentDate,
            createdAt: currentDate,
            modifiedAt: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of CropProcurementPass', () => {
        const returnedFromService = Object.assign(
          {
            cropDate: currentDate.format(DATE_TIME_FORMAT),
            cropType: 'BBBBBB',
            expectedYeild: 'BBBBBB',
            expectedYeildUnits: 'BBBBBB',
            procurementDate: currentDate.format(DATE_TIME_FORMAT),
            passStatus: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            cropDate: currentDate,
            procurementDate: currentDate,
            createdAt: currentDate,
            modifiedAt: currentDate
          },
          returnedFromService
        );
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

      it('should delete a CropProcurementPass', () => {
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
