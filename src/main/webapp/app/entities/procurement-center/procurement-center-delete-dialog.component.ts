import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProcurementCenter } from 'app/shared/model/procurement-center.model';
import { ProcurementCenterService } from './procurement-center.service';

@Component({
  selector: 'jhi-procurement-center-delete-dialog',
  templateUrl: './procurement-center-delete-dialog.component.html'
})
export class ProcurementCenterDeleteDialogComponent {
  procurementCenter: IProcurementCenter;

  constructor(
    protected procurementCenterService: ProcurementCenterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.procurementCenterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'procurementCenterListModification',
        content: 'Deleted an procurementCenter'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-procurement-center-delete-popup',
  template: ''
})
export class ProcurementCenterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ procurementCenter }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProcurementCenterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.procurementCenter = procurementCenter;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/procurement-center', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/procurement-center', { outlets: { popup: null } }]);
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
