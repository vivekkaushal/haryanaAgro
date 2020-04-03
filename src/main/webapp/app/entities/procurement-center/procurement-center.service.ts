import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProcurementCenter } from 'app/shared/model/procurement-center.model';

type EntityResponseType = HttpResponse<IProcurementCenter>;
type EntityArrayResponseType = HttpResponse<IProcurementCenter[]>;

@Injectable({ providedIn: 'root' })
export class ProcurementCenterService {
  public resourceUrl = SERVER_API_URL + 'api/procurement-centers';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/procurement-centers';

  constructor(protected http: HttpClient) {}

  create(procurementCenter: IProcurementCenter): Observable<EntityResponseType> {
    return this.http.post<IProcurementCenter>(this.resourceUrl, procurementCenter, { observe: 'response' });
  }

  update(procurementCenter: IProcurementCenter): Observable<EntityResponseType> {
    return this.http.put<IProcurementCenter>(this.resourceUrl, procurementCenter, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProcurementCenter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProcurementCenter[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProcurementCenter[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
