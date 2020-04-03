import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HaryanaAgroSharedModule } from 'app/shared/shared.module';
import { CropProcurementPassComponent } from './crop-procurement-pass.component';
import { CropProcurementPassDetailComponent } from './crop-procurement-pass-detail.component';
import { CropProcurementPassUpdateComponent } from './crop-procurement-pass-update.component';
import {
  CropProcurementPassDeletePopupComponent,
  CropProcurementPassDeleteDialogComponent
} from './crop-procurement-pass-delete-dialog.component';
import { cropProcurementPassRoute, cropProcurementPassPopupRoute } from './crop-procurement-pass.route';

const ENTITY_STATES = [...cropProcurementPassRoute, ...cropProcurementPassPopupRoute];

@NgModule({
  imports: [HaryanaAgroSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CropProcurementPassComponent,
    CropProcurementPassDetailComponent,
    CropProcurementPassUpdateComponent,
    CropProcurementPassDeleteDialogComponent,
    CropProcurementPassDeletePopupComponent
  ],
  entryComponents: [CropProcurementPassDeleteDialogComponent]
})
export class HaryanaAgroCropProcurementPassModule {}
