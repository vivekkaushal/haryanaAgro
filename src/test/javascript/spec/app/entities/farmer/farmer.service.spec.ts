import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { FarmerService } from 'app/entities/farmer/farmer.service';
import { IFarmer, Farmer } from 'app/shared/model/farmer.model';
import { Title } from 'app/shared/model/enumerations/title.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { FarmingType } from 'app/shared/model/enumerations/farming-type.model';

describe('Service Tests', () => {
  describe('Farmer Service', () => {
    let injector: TestBed;
    let service: FarmerService;
    let httpMock: HttpTestingController;
    let elemDefault: IFarmer;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FarmerService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Farmer(
        0,
        Title.Mr,
        'AAAAAAA',
        Gender.Male,
        FarmingType.Lease,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a Farmer', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Farmer(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Farmer', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            name: 'BBBBBB',
            gender: 'BBBBBB',
            farmerType: 'BBBBBB',
            adhaarNumber: 'BBBBBB',
            pan: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            signiture: 'BBBBBB',
            address: 'BBBBBB',
            houseNumber: 'BBBBBB',
            street: 'BBBBBB',
            landmark: 'BBBBBB',
            village: 'BBBBBB',
            tehsil: 'BBBBBB',
            district: 'BBBBBB',
            pincode: 'BBBBBB',
            khataNumber: 'BBBBBB',
            surveyNumber: 'BBBBBB',
            bankbookScanImageUrl: 'BBBBBB',
            accountHolderNameAsPerBank: 'BBBBBB',
            accountNumber: 'BBBBBB',
            ifsc: 'BBBBBB',
            bankName: 'BBBBBB',
            branch: 'BBBBBB'
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

      it('should return a list of Farmer', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            name: 'BBBBBB',
            gender: 'BBBBBB',
            farmerType: 'BBBBBB',
            adhaarNumber: 'BBBBBB',
            pan: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            signiture: 'BBBBBB',
            address: 'BBBBBB',
            houseNumber: 'BBBBBB',
            street: 'BBBBBB',
            landmark: 'BBBBBB',
            village: 'BBBBBB',
            tehsil: 'BBBBBB',
            district: 'BBBBBB',
            pincode: 'BBBBBB',
            khataNumber: 'BBBBBB',
            surveyNumber: 'BBBBBB',
            bankbookScanImageUrl: 'BBBBBB',
            accountHolderNameAsPerBank: 'BBBBBB',
            accountNumber: 'BBBBBB',
            ifsc: 'BBBBBB',
            bankName: 'BBBBBB',
            branch: 'BBBBBB'
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

      it('should delete a Farmer', () => {
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
