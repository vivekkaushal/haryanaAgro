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
import { ICropProcurementPass, CropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';
import { CropProcurementPassService } from './crop-procurement-pass.service';
import { IProcurementCenter } from 'app/shared/model/procurement-center.model';
import { ProcurementCenterService } from 'app/entities/procurement-center/procurement-center.service';
import { IFarmer } from 'app/shared/model/farmer.model';
import { FarmerService } from 'app/entities/farmer/farmer.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';

@Component({
  selector: 'jhi-crop-procurement-pass-update',
  templateUrl: './crop-procurement-pass-update.component.html'
})
export class CropProcurementPassUpdateComponent implements OnInit {
  isSaving: boolean;

  procurementcenters: IProcurementCenter[];

  farmers: IFarmer[];

  employees: IEmployee[];

  editForm = this.fb.group({
    id: [],
    cropDate: [],
    cropType: [],
    expectedYeild: [],
    expectedYeildUnits: [],
    procurementDate: [],
    passStatus: [],
    createdAt: [],
    modifiedAt: [],
    procurementCenterId: [],
    farmerId: [],
    passApprovedById: [],
    createdById: [],
    modifiedById: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected cropProcurementPassService: CropProcurementPassService,
    protected procurementCenterService: ProcurementCenterService,
    protected farmerService: FarmerService,
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cropProcurementPass }) => {
      this.updateForm(cropProcurementPass);
    });
    this.procurementCenterService
      .query()
      .subscribe(
        (res: HttpResponse<IProcurementCenter[]>) => (this.procurementcenters = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.farmerService
      .query()
      .subscribe((res: HttpResponse<IFarmer[]>) => (this.farmers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.employeeService
      .query()
      .subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(cropProcurementPass: ICropProcurementPass) {
    this.editForm.patchValue({
      id: cropProcurementPass.id,
      cropDate: cropProcurementPass.cropDate != null ? cropProcurementPass.cropDate.format(DATE_TIME_FORMAT) : null,
      cropType: cropProcurementPass.cropType,
      expectedYeild: cropProcurementPass.expectedYeild,
      expectedYeildUnits: cropProcurementPass.expectedYeildUnits,
      procurementDate: cropProcurementPass.procurementDate != null ? cropProcurementPass.procurementDate.format(DATE_TIME_FORMAT) : null,
      passStatus: cropProcurementPass.passStatus,
      createdAt: cropProcurementPass.createdAt != null ? cropProcurementPass.createdAt.format(DATE_TIME_FORMAT) : null,
      modifiedAt: cropProcurementPass.modifiedAt != null ? cropProcurementPass.modifiedAt.format(DATE_TIME_FORMAT) : null,
      procurementCenterId: cropProcurementPass.procurementCenterId,
      farmerId: cropProcurementPass.farmerId,
      passApprovedById: cropProcurementPass.passApprovedById,
      createdById: cropProcurementPass.createdById,
      modifiedById: cropProcurementPass.modifiedById
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const cropProcurementPass = this.createFromForm();
    if (cropProcurementPass.id !== undefined) {
      this.subscribeToSaveResponse(this.cropProcurementPassService.update(cropProcurementPass));
    } else {
      this.subscribeToSaveResponse(this.cropProcurementPassService.create(cropProcurementPass));
    }
  }

  private createFromForm(): ICropProcurementPass {
    return {
      ...new CropProcurementPass(),
      id: this.editForm.get(['id']).value,
      cropDate: this.editForm.get(['cropDate']).value != null ? moment(this.editForm.get(['cropDate']).value, DATE_TIME_FORMAT) : undefined,
      cropType: this.editForm.get(['cropType']).value,
      expectedYeild: this.editForm.get(['expectedYeild']).value,
      expectedYeildUnits: this.editForm.get(['expectedYeildUnits']).value,
      procurementDate:
        this.editForm.get(['procurementDate']).value != null
          ? moment(this.editForm.get(['procurementDate']).value, DATE_TIME_FORMAT)
          : undefined,
      passStatus: this.editForm.get(['passStatus']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      modifiedAt:
        this.editForm.get(['modifiedAt']).value != null ? moment(this.editForm.get(['modifiedAt']).value, DATE_TIME_FORMAT) : undefined,
      procurementCenterId: this.editForm.get(['procurementCenterId']).value,
      farmerId: this.editForm.get(['farmerId']).value,
      passApprovedById: this.editForm.get(['passApprovedById']).value,
      createdById: this.editForm.get(['createdById']).value,
      modifiedById: this.editForm.get(['modifiedById']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICropProcurementPass>>) {
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

  trackProcurementCenterById(index: number, item: IProcurementCenter) {
    return item.id;
  }

  trackFarmerById(index: number, item: IFarmer) {
    return item.id;
  }

  trackEmployeeById(index: number, item: IEmployee) {
    return item.id;
  }
}
