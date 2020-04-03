import { Title } from 'app/shared/model/enumerations/title.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { FarmingType } from 'app/shared/model/enumerations/farming-type.model';

export interface IFarmer {
  id?: number;
  title?: Title;
  name?: string;
  gender?: Gender;
  farmerType?: FarmingType;
  adhaarNumber?: string;
  pan?: string;
  phoneNumber?: string;
  signiture?: string;
  address?: string;
  houseNumber?: string;
  street?: string;
  landmark?: string;
  village?: string;
  tehsil?: string;
  district?: string;
  pincode?: string;
  khataNumber?: string;
  surveyNumber?: string;
  bankbookScanImageUrl?: string;
  accountHolderNameAsPerBank?: string;
  accountNumber?: string;
  ifsc?: string;
  bankName?: string;
  branch?: string;
  userId?: number;
}

export class Farmer implements IFarmer {
  constructor(
    public id?: number,
    public title?: Title,
    public name?: string,
    public gender?: Gender,
    public farmerType?: FarmingType,
    public adhaarNumber?: string,
    public pan?: string,
    public phoneNumber?: string,
    public signiture?: string,
    public address?: string,
    public houseNumber?: string,
    public street?: string,
    public landmark?: string,
    public village?: string,
    public tehsil?: string,
    public district?: string,
    public pincode?: string,
    public khataNumber?: string,
    public surveyNumber?: string,
    public bankbookScanImageUrl?: string,
    public accountHolderNameAsPerBank?: string,
    public accountNumber?: string,
    public ifsc?: string,
    public bankName?: string,
    public branch?: string,
    public userId?: number
  ) {}
}
