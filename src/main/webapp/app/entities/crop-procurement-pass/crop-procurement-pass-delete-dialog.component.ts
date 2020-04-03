import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';
import { CropProcurementPassService } from './crop-procurement-pass.service';

@Component({
  selector: 'jhi-crop-procurement-pass-delete-dialog',
  templateUrl: './crop-procurement-pass-delete-dialog.component.html'
})
export class CropProcurementPassDeleteDialogComponent {
  cropProcurementPass: ICropProcurementPass;

  constructor(
    protected cropProcurementPassService: CropProcurementPassService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.cropProcurementPassService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'cropProcurementPassListModification',
        content: 'Deleted an cropProcurementPass'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-crop-procurement-pass-delete-popup',
  template: ''
})
export class CropProcurementPassDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cropProcurementPass }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CropProcurementPassDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.cropProcurementPass = cropProcurementPass;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/crop-procurement-pass', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/crop-procurement-pass', { outlets: { popup: null } }]);
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
