export interface ILocation {
  id?: number;
  state?: string;
  district?: string;
  tehsil?: string;
  village?: string;
  pincode?: string;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public state?: string,
    public district?: string,
    public tehsil?: string,
    public village?: string,
    public pincode?: string
  ) {}
}
