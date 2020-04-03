import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProcurementCenter } from 'app/shared/model/procurement-center.model';

@Component({
  selector: 'jhi-procurement-center-detail',
  templateUrl: './procurement-center-detail.component.html'
})
export class ProcurementCenterDetailComponent implements OnInit {
  procurementCenter: IProcurementCenter;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ procurementCenter }) => {
      this.procurementCenter = procurementCenter;
    });
  }

  previousState() {
    window.history.back();
  }
}
