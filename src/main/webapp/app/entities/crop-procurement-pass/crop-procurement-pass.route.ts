import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';
import { CropProcurementPassService } from './crop-procurement-pass.service';
import { CropProcurementPassComponent } from './crop-procurement-pass.component';
import { CropProcurementPassDetailComponent } from './crop-procurement-pass-detail.component';
import { CropProcurementPassUpdateComponent } from './crop-procurement-pass-update.component';
import { CropProcurementPassDeletePopupComponent } from './crop-procurement-pass-delete-dialog.component';
import { ICropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

@Injectable({ providedIn: 'root' })
export class CropProcurementPassResolve implements Resolve<ICropProcurementPass> {
  constructor(private service: CropProcurementPassService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICropProcurementPass> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((cropProcurementPass: HttpResponse<CropProcurementPass>) => cropProcurementPass.body));
    }
    return of(new CropProcurementPass());
  }
}

export const cropProcurementPassRoute: Routes = [
  {
    path: '',
    component: CropProcurementPassComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CropProcurementPasses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CropProcurementPassDetailComponent,
    resolve: {
      cropProcurementPass: CropProcurementPassResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CropProcurementPasses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CropProcurementPassUpdateComponent,
    resolve: {
      cropProcurementPass: CropProcurementPassResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CropProcurementPasses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CropProcurementPassUpdateComponent,
    resolve: {
      cropProcurementPass: CropProcurementPassResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CropProcurementPasses'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cropProcurementPassPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CropProcurementPassDeletePopupComponent,
    resolve: {
      cropProcurementPass: CropProcurementPassResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CropProcurementPasses'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
