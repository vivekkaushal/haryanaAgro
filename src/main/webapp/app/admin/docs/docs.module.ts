import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HaryanaAgroSharedModule } from 'app/shared/shared.module';

import { JhiDocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [HaryanaAgroSharedModule, RouterModule.forChild([docsRoute])],
  declarations: [JhiDocsComponent]
})
export class DocsModule {}
