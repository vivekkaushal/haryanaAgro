import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HaryanaAgroSharedModule } from 'app/shared/shared.module';
import { FarmerComponent } from './farmer.component';
import { FarmerDetailComponent } from './farmer-detail.component';
import { FarmerUpdateComponent } from './farmer-update.component';
import { FarmerDeletePopupComponent, FarmerDeleteDialogComponent } from './farmer-delete-dialog.component';
import { farmerRoute, farmerPopupRoute } from './farmer.route';

const ENTITY_STATES = [...farmerRoute, ...farmerPopupRoute];

@NgModule({
  imports: [HaryanaAgroSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [FarmerComponent, FarmerDetailComponent, FarmerUpdateComponent, FarmerDeleteDialogComponent, FarmerDeletePopupComponent],
  entryComponents: [FarmerDeleteDialogComponent]
})
export class HaryanaAgroFarmerModule {}
