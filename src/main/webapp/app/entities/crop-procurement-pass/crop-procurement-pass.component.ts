import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { ICropProcurementPass } from 'app/shared/model/crop-procurement-pass.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CropProcurementPassService } from './crop-procurement-pass.service';

@Component({
  selector: 'jhi-crop-procurement-pass',
  templateUrl: './crop-procurement-pass.component.html'
})
export class CropProcurementPassComponent implements OnInit, OnDestroy {
  cropProcurementPasses: ICropProcurementPass[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;
  currentSearch: string;

  constructor(
    protected cropProcurementPassService: CropProcurementPassService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute
  ) {
    this.cropProcurementPasses = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.cropProcurementPassService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort()
        })
        .subscribe((res: HttpResponse<ICropProcurementPass[]>) => this.paginateCropProcurementPasses(res.body, res.headers));
      return;
    }
    this.cropProcurementPassService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICropProcurementPass[]>) => this.paginateCropProcurementPasses(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.cropProcurementPasses = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  clear() {
    this.cropProcurementPasses = [];
    this.links = {
      last: 0
    };
    this.page = 0;
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch = '';
    this.loadAll();
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.cropProcurementPasses = [];
    this.links = {
      last: 0
    };
    this.page = 0;
    this.predicate = '_score';
    this.reverse = false;
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCropProcurementPasses();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICropProcurementPass) {
    return item.id;
  }

  registerChangeInCropProcurementPasses() {
    this.eventSubscriber = this.eventManager.subscribe('cropProcurementPassListModification', () => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCropProcurementPasses(data: ICropProcurementPass[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.cropProcurementPasses.push(data[i]);
    }
  }
}
