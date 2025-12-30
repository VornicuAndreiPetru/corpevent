import {Component, OnInit} from '@angular/core';
import {SefService} from '../../../core/services/sef.service';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-sef-organigrama',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './sef-organigrama.component.html',
  standalone: true,
  styleUrl: './sef-organigrama.component.scss'
})
export class SefOrganigramaComponent implements OnInit{

  sefName = '';
  execs: any[] = [];
  loading = false;

  constructor(private sefService: SefService) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.loading = true;

    this.sefService.getMyOrganigram().subscribe({
      next: res => {
        // backend-ul trimite DIRECT execii
        this.execs = res;

        // afișăm username-ul șefului
        this.sefName = localStorage.getItem('username') || 'Șef';

        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

}
