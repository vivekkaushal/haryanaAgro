import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.HaryanaAgroEmployeeModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.HaryanaAgroLocationModule)
      },
      {
        path: 'farmer',
        loadChildren: () => import('./farmer/farmer.module').then(m => m.HaryanaAgroFarmerModule)
      },
      {
        path: 'procurement-center',
        loadChildren: () => import('./procurement-center/procurement-center.module').then(m => m.HaryanaAgroProcurementCenterModule)
      },
      {
        path: 'crop-procurement-pass',
        loadChildren: () => import('./crop-procurement-pass/crop-procurement-pass.module').then(m => m.HaryanaAgroCropProcurementPassModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class HaryanaAgroEntityModule {}
