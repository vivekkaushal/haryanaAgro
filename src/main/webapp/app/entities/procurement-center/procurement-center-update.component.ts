import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProcurementCenter, ProcurementCenter } from 'app/shared/model/procurement-center.model';
import { ProcurementCenterService } from './procurement-center.service';

@Component({
  selector: 'jhi-procurement-center-update',
  templateUrl: './procurement-center-update.component.html'
})
export class ProcurementCenterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    state: [],
    district: [],
    tehsil: [],
    village: [],
    centerType: [],
    centerIdentificationNumber: []
  });

  constructor(
    protected procurementCenterService: ProcurementCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ procurementCenter }) => {
      this.updateForm(procurementCenter);
    });
  }

  updateForm(procurementCenter: IProcurementCenter) {
    this.editForm.patchValue({
      id: procurementCenter.id,
      state: procurementCenter.state,
      district: procurementCenter.district,
      tehsil: procurementCenter.tehsil,
      village: procurementCenter.village,
      centerType: procurementCenter.centerType,
      centerIdentificationNumber: procurementCenter.centerIdentificationNumber
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const procurementCenter = this.createFromForm();
    if (procurementCenter.id !== undefined) {
      this.subscribeToSaveResponse(this.procurementCenterService.update(procurementCenter));
    } else {
      this.subscribeToSaveResponse(this.procurementCenterService.create(procurementCenter));
    }
  }

  private createFromForm(): IProcurementCenter {
    return {
      ...new ProcurementCenter(),
      id: this.editForm.get(['id']).value,
      state: this.editForm.get(['state']).value,
      district: this.editForm.get(['district']).value,
      tehsil: this.editForm.get(['tehsil']).value,
      village: this.editForm.get(['village']).value,
      centerType: this.editForm.get(['centerType']).value,
      centerIdentificationNumber: this.editForm.get(['centerIdentificationNumber']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProcurementCenter>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
