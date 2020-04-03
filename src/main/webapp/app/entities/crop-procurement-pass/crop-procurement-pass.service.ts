import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

type EntityResponseType = HttpResponse<ICropProcurementPass>;
type EntityArrayResponseType = HttpResponse<ICropProcurementPass[]>;

@Injectable({ providedIn: 'root' })
export class CropProcurementPassService {
  public resourceUrl = SERVER_API_URL + 'api/crop-procurement-passes';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/crop-procurement-passes';

  constructor(protected http: HttpClient) {}

  create(cropProcurementPass: ICropProcurementPass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cropProcurementPass);
    return this.http
      .post<ICropProcurementPass>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cropProcurementPass: ICropProcurementPass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cropProcurementPass);
    return this.http
      .put<ICropProcurementPass>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICropProcurementPass>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICropProcurementPass[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICropProcurementPass[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(cropProcurementPass: ICropProcurementPass): ICropProcurementPass {
    const copy: ICropProcurementPass = Object.assign({}, cropProcurementPass, {
      cropDate:
        cropProcurementPass.cropDate != null && cropProcurementPass.cropDate.isValid() ? cropProcurementPass.cropDate.toJSON() : null,
      procurementDate:
        cropProcurementPass.procurementDate != null && cropProcurementPass.procurementDate.isValid()
          ? cropProcurementPass.procurementDate.toJSON()
          : null,
      createdAt:
        cropProcurementPass.createdAt != null && cropProcurementPass.createdAt.isValid() ? cropProcurementPass.createdAt.toJSON() : null,
      modifiedAt:
        cropProcurementPass.modifiedAt != null && cropProcurementPass.modifiedAt.isValid() ? cropProcurementPass.modifiedAt.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.cropDate = res.body.cropDate != null ? moment(res.body.cropDate) : null;
      res.body.procurementDate = res.body.procurementDate != null ? moment(res.body.procurementDate) : null;
      res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
      res.body.modifiedAt = res.body.modifiedAt != null ? moment(res.body.modifiedAt) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cropProcurementPass: ICropProcurementPass) => {
        cropProcurementPass.cropDate = cropProcurementPass.cropDate != null ? moment(cropProcurementPass.cropDate) : null;
        cropProcurementPass.procurementDate =
          cropProcurementPass.procurementDate != null ? moment(cropProcurementPass.procurementDate) : null;
        cropProcurementPass.createdAt = cropProcurementPass.createdAt != null ? moment(cropProcurementPass.createdAt) : null;
        cropProcurementPass.modifiedAt = cropProcurementPass.modifiedAt != null ? moment(cropProcurementPass.modifiedAt) : null;
      });
    }
    return res;
  }
}
