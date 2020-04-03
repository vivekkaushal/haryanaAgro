import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFarmer } from 'app/shared/model/farmer.model';

@Component({
  selector: 'jhi-farmer-detail',
  templateUrl: './farmer-detail.component.html'
})
export class FarmerDetailComponent implements OnInit {
  farmer: IFarmer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ farmer }) => {
      this.farmer = farmer;
    });
  }

  previousState() {
    window.history.back();
  }
}
