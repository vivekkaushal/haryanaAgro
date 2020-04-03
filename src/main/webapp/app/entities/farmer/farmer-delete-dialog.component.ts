import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFarmer } from 'app/shared/model/farmer.model';
import { FarmerService } from './farmer.service';

@Component({
  selector: 'jhi-farmer-delete-dialog',
  templateUrl: './farmer-delete-dialog.component.html'
})
export class FarmerDeleteDialogComponent {
  farmer: IFarmer;

  constructor(protected farmerService: FarmerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.farmerService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'farmerListModification',
        content: 'Deleted an farmer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-farmer-delete-popup',
  template: ''
})
export class FarmerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ farmer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FarmerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.farmer = farmer;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/farmer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/farmer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
