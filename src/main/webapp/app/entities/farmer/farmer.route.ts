import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Farmer } from 'app/shared/model/farmer.model';
import { FarmerService } from './farmer.service';
import { FarmerComponent } from './farmer.component';
import { FarmerDetailComponent } from './farmer-detail.component';
import { FarmerUpdateComponent } from './farmer-update.component';
import { FarmerDeletePopupComponent } from './farmer-delete-dialog.component';
import { IFarmer } from 'app/shared/model/farmer.model';

@Injectable({ providedIn: 'root' })
export class FarmerResolve implements Resolve<IFarmer> {
  constructor(private service: FarmerService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFarmer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((farmer: HttpResponse<Farmer>) => farmer.body));
    }
    return of(new Farmer());
  }
}

export const farmerRoute: Routes = [
  {
    path: '',
    component: FarmerComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Farmers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FarmerDetailComponent,
    resolve: {
      farmer: FarmerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Farmers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FarmerUpdateComponent,
    resolve: {
      farmer: FarmerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Farmers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FarmerUpdateComponent,
    resolve: {
      farmer: FarmerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Farmers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const farmerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FarmerDeletePopupComponent,
    resolve: {
      farmer: FarmerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Farmers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
