import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EmployeeService } from 'app/entities/employee/employee.service';
import { IEmployee, Employee } from 'app/shared/model/employee.model';
import { Title } from 'app/shared/model/enumerations/title.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { Designation } from 'app/shared/model/enumerations/designation.model';
import { EmployeeType } from 'app/shared/model/enumerations/employee-type.model';

describe('Service Tests', () => {
  describe('Employee Service', () => {
    let injector: TestBed;
    let service: EmployeeService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmployee;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EmployeeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Employee(
        0,
        Title.Mr,
        'AAAAAAA',
        Gender.Male,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        Designation.AEO,
        EmployeeType.Permanent,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            joiningdate: currentDate.format(DATE_TIME_FORMAT),
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Employee', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            joiningdate: currentDate.format(DATE_TIME_FORMAT),
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            joiningdate: currentDate,
            dateOfBirth: currentDate
          },
          returnedFromService
        );
        service
          .create(new Employee(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Employee', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            name: 'BBBBBB',
            gender: 'BBBBBB',
            employeeCode: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            joiningdate: currentDate.format(DATE_TIME_FORMAT),
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            designation: 'BBBBBB',
            employeeType: 'BBBBBB',
            adhaarNumber: 'BBBBBB',
            pan: 'BBBBBB',
            signiture: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joiningdate: currentDate,
            dateOfBirth: currentDate
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

      it('should return a list of Employee', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            name: 'BBBBBB',
            gender: 'BBBBBB',
            employeeCode: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            joiningdate: currentDate.format(DATE_TIME_FORMAT),
            dateOfBirth: currentDate.format(DATE_TIME_FORMAT),
            designation: 'BBBBBB',
            employeeType: 'BBBBBB',
            adhaarNumber: 'BBBBBB',
            pan: 'BBBBBB',
            signiture: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            joiningdate: currentDate,
            dateOfBirth: currentDate
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

      it('should delete a Employee', () => {
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
