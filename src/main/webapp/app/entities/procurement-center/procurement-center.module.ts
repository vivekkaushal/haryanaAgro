import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HaryanaAgroSharedModule } from 'app/shared/shared.module';
import { ProcurementCenterComponent } from './procurement-center.component';
import { ProcurementCenterDetailComponent } from './procurement-center-detail.component';
import { ProcurementCenterUpdateComponent } from './procurement-center-update.component';
import {
  ProcurementCenterDeletePopupComponent,
  ProcurementCenterDeleteDialogComponent
} from './procurement-center-delete-dialog.component';
import { procurementCenterRoute, procurementCenterPopupRoute } from './procurement-center.route';

const ENTITY_STATES = [...procurementCenterRoute, ...procurementCenterPopupRoute];

@NgModule({
  imports: [HaryanaAgroSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProcurementCenterComponent,
    ProcurementCenterDetailComponent,
    ProcurementCenterUpdateComponent,
    ProcurementCenterDeleteDialogComponent,
    ProcurementCenterDeletePopupComponent
  ],
  entryComponents: [ProcurementCenterDeleteDialogComponent]
})
export class HaryanaAgroProcurementCenterModule {}
