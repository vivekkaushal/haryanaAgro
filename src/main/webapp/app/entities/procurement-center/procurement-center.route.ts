import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProcurementCenter } from 'app/shared/model/procurement-center.model';
import { ProcurementCenterService } from './procurement-center.service';
import { ProcurementCenterComponent } from './procurement-center.component';
import { ProcurementCenterDetailComponent } from './procurement-center-detail.component';
import { ProcurementCenterUpdateComponent } from './procurement-center-update.component';
import { ProcurementCenterDeletePopupComponent } from './procurement-center-delete-dialog.component';
import { IProcurementCenter } from 'app/shared/model/procurement-center.model';

@Injectable({ providedIn: 'root' })
export class ProcurementCenterResolve implements Resolve<IProcurementCenter> {
  constructor(private service: ProcurementCenterService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProcurementCenter> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((procurementCenter: HttpResponse<ProcurementCenter>) => procurementCenter.body));
    }
    return of(new ProcurementCenter());
  }
}

export const procurementCenterRoute: Routes = [
  {
    path: '',
    component: ProcurementCenterComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProcurementCenters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProcurementCenterDetailComponent,
    resolve: {
      procurementCenter: ProcurementCenterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProcurementCenters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProcurementCenterUpdateComponent,
    resolve: {
      procurementCenter: ProcurementCenterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProcurementCenters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProcurementCenterUpdateComponent,
    resolve: {
      procurementCenter: ProcurementCenterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProcurementCenters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const procurementCenterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProcurementCenterDeletePopupComponent,
    resolve: {
      procurementCenter: ProcurementCenterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ProcurementCenters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
