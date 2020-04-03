import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFarmer } from 'app/shared/model/farmer.model';

type EntityResponseType = HttpResponse<IFarmer>;
type EntityArrayResponseType = HttpResponse<IFarmer[]>;

@Injectable({ providedIn: 'root' })
export class FarmerService {
  public resourceUrl = SERVER_API_URL + 'api/farmers';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/farmers';

  constructor(protected http: HttpClient) {}

  create(farmer: IFarmer): Observable<EntityResponseType> {
    return this.http.post<IFarmer>(this.resourceUrl, farmer, { observe: 'response' });
  }

  update(farmer: IFarmer): Observable<EntityResponseType> {
    return this.http.put<IFarmer>(this.resourceUrl, farmer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFarmer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFarmer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFarmer[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
