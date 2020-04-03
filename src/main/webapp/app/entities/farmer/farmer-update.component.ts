import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IFarmer, Farmer } from 'app/shared/model/farmer.model';
import { FarmerService } from './farmer.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-farmer-update',
  templateUrl: './farmer-update.component.html'
})
export class FarmerUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    title: [],
    name: [],
    gender: [],
    farmerType: [],
    adhaarNumber: [],
    pan: [],
    phoneNumber: [],
    signiture: [],
    address: [],
    houseNumber: [],
    street: [],
    landmark: [],
    village: [],
    tehsil: [],
    district: [],
    pincode: [],
    khataNumber: [],
    surveyNumber: [],
    bankbookScanImageUrl: [],
    accountHolderNameAsPerBank: [],
    accountNumber: [],
    ifsc: [],
    bankName: [],
    branch: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected farmerService: FarmerService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ farmer }) => {
      this.updateForm(farmer);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(farmer: IFarmer) {
    this.editForm.patchValue({
      id: farmer.id,
      title: farmer.title,
      name: farmer.name,
      gender: farmer.gender,
      farmerType: farmer.farmerType,
      adhaarNumber: farmer.adhaarNumber,
      pan: farmer.pan,
      phoneNumber: farmer.phoneNumber,
      signiture: farmer.signiture,
      address: farmer.address,
      houseNumber: farmer.houseNumber,
      street: farmer.street,
      landmark: farmer.landmark,
      village: farmer.village,
      tehsil: farmer.tehsil,
      district: farmer.district,
      pincode: farmer.pincode,
      khataNumber: farmer.khataNumber,
      surveyNumber: farmer.surveyNumber,
      bankbookScanImageUrl: farmer.bankbookScanImageUrl,
      accountHolderNameAsPerBank: farmer.accountHolderNameAsPerBank,
      accountNumber: farmer.accountNumber,
      ifsc: farmer.ifsc,
      bankName: farmer.bankName,
      branch: farmer.branch,
      userId: farmer.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const farmer = this.createFromForm();
    if (farmer.id !== undefined) {
      this.subscribeToSaveResponse(this.farmerService.update(farmer));
    } else {
      this.subscribeToSaveResponse(this.farmerService.create(farmer));
    }
  }

  private createFromForm(): IFarmer {
    return {
      ...new Farmer(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      name: this.editForm.get(['name']).value,
      gender: this.editForm.get(['gender']).value,
      farmerType: this.editForm.get(['farmerType']).value,
      adhaarNumber: this.editForm.get(['adhaarNumber']).value,
      pan: this.editForm.get(['pan']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value,
      signiture: this.editForm.get(['signiture']).value,
      address: this.editForm.get(['address']).value,
      houseNumber: this.editForm.get(['houseNumber']).value,
      street: this.editForm.get(['street']).value,
      landmark: this.editForm.get(['landmark']).value,
      village: this.editForm.get(['village']).value,
      tehsil: this.editForm.get(['tehsil']).value,
      district: this.editForm.get(['district']).value,
      pincode: this.editForm.get(['pincode']).value,
      khataNumber: this.editForm.get(['khataNumber']).value,
      surveyNumber: this.editForm.get(['surveyNumber']).value,
      bankbookScanImageUrl: this.editForm.get(['bankbookScanImageUrl']).value,
      accountHolderNameAsPerBank: this.editForm.get(['accountHolderNameAsPerBank']).value,
      accountNumber: this.editForm.get(['accountNumber']).value,
      ifsc: this.editForm.get(['ifsc']).value,
      bankName: this.editForm.get(['bankName']).value,
      branch: this.editForm.get(['branch']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFarmer>>) {
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
