import { Moment } from 'moment';
import { Title } from 'app/shared/model/enumerations/title.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { Designation } from 'app/shared/model/enumerations/designation.model';
import { EmployeeType } from 'app/shared/model/enumerations/employee-type.model';

export interface IEmployee {
  id?: number;
  title?: Title;
  name?: string;
  gender?: Gender;
  employeeCode?: string;
  phoneNumber?: string;
  joiningdate?: Moment;
  dateOfBirth?: Moment;
  designation?: Designation;
  employeeType?: EmployeeType;
  adhaarNumber?: string;
  pan?: string;
  signiture?: string;
  userId?: number;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public title?: Title,
    public name?: string,
    public gender?: Gender,
    public employeeCode?: string,
    public phoneNumber?: string,
    public joiningdate?: Moment,
    public dateOfBirth?: Moment,
    public designation?: Designation,
    public employeeType?: EmployeeType,
    public adhaarNumber?: string,
    public pan?: string,
    public signiture?: string,
    public userId?: number
  ) {}
}
