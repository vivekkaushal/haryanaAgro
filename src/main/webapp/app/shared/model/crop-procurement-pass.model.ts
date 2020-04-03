import { Moment } from 'moment';
import { CropType } from 'app/shared/model/enumerations/crop-type.model';
import { ExpectedYieldUnit } from 'app/shared/model/enumerations/expected-yield-unit.model';
import { PassStatus } from 'app/shared/model/enumerations/pass-status.model';

export interface ICropProcurementPass {
  id?: number;
  cropDate?: Moment;
  cropType?: CropType;
  expectedYeild?: string;
  expectedYeildUnits?: ExpectedYieldUnit;
  procurementDate?: Moment;
  passStatus?: PassStatus;
  createdAt?: Moment;
  modifiedAt?: Moment;
  procurementCenterId?: number;
  farmerId?: number;
  passApprovedById?: number;
  createdById?: number;
  modifiedById?: number;
}

export class CropProcurementPass implements ICropProcurementPass {
  constructor(
    public id?: number,
    public cropDate?: Moment,
    public cropType?: CropType,
    public expectedYeild?: string,
    public expectedYeildUnits?: ExpectedYieldUnit,
    public procurementDate?: Moment,
    public passStatus?: PassStatus,
    public createdAt?: Moment,
    public modifiedAt?: Moment,
    public procurementCenterId?: number,
    public farmerId?: number,
    public passApprovedById?: number,
    public createdById?: number,
    public modifiedById?: number
  ) {}
}
