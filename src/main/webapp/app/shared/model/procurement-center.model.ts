import { CenterType } from 'app/shared/model/enumerations/center-type.model';

export interface IProcurementCenter {
  id?: number;
  state?: string;
  district?: string;
  tehsil?: string;
  village?: string;
  centerType?: CenterType;
  centerIdentificationNumber?: string;
}

export class ProcurementCenter implements IProcurementCenter {
  constructor(
    public id?: number,
    public state?: string,
    public district?: string,
    public tehsil?: string,
    public village?: string,
    public centerType?: CenterType,
    public centerIdentificationNumber?: string
  ) {}
}
