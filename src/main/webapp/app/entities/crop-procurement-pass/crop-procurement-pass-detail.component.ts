import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

@Component({
  selector: 'jhi-crop-procurement-pass-detail',
  templateUrl: './crop-procurement-pass-detail.component.html'
})
export class CropProcurementPassDetailComponent implements OnInit {
  cropProcurementPass: ICropProcurementPass;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cropProcurementPass }) => {
      this.cropProcurementPass = cropProcurementPass;
    });
  }

  previousState() {
    window.history.back();
  }
}
