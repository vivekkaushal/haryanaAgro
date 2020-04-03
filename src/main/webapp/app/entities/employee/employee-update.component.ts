import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEmployee, Employee } from 'app/shared/model/employee.model';
import { EmployeeService } from './employee.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-employee-update',
  templateUrl: './employee-update.component.html'
})
export class EmployeeUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    title: [],
    name: [],
    gender: [],
    employeeCode: [],
    phoneNumber: [],
    joiningdate: [],
    dateOfBirth: [],
    designation: [],
    employeeType: [],
    adhaarNumber: [],
    pan: [],
    signiture: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected employeeService: EmployeeService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ employee }) => {
      this.updateForm(employee);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(employee: IEmployee) {
    this.editForm.patchValue({
      id: employee.id,
      title: employee.title,
      name: employee.name,
      gender: employee.gender,
      employeeCode: employee.employeeCode,
      phoneNumber: employee.phoneNumber,
      joiningdate: employee.joiningdate != null ? employee.joiningdate.format(DATE_TIME_FORMAT) : null,
      dateOfBirth: employee.dateOfBirth != null ? employee.dateOfBirth.format(DATE_TIME_FORMAT) : null,
      designation: employee.designation,
      employeeType: employee.employeeType,
      adhaarNumber: employee.adhaarNumber,
      pan: employee.pan,
      signiture: employee.signiture,
      userId: employee.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const employee = this.createFromForm();
    if (employee.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeService.update(employee));
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }
  }

  private createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      name: this.editForm.get(['name']).value,
      gender: this.editForm.get(['gender']).value,
      employeeCode: this.editForm.get(['employeeCode']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value,
      joiningdate:
        this.editForm.get(['joiningdate']).value != null ? moment(this.editForm.get(['joiningdate']).value, DATE_TIME_FORMAT) : undefined,
      dateOfBirth:
        this.editForm.get(['dateOfBirth']).value != null ? moment(this.editForm.get(['dateOfBirth']).value, DATE_TIME_FORMAT) : undefined,
      designation: this.editForm.get(['designation']).value,
      employeeType: this.editForm.get(['employeeType']).value,
      adhaarNumber: this.editForm.get(['adhaarNumber']).value,
      pan: this.editForm.get(['pan']).value,
      signiture: this.editForm.get(['signiture']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
