import {Component, OnInit} from '@angular/core';
import {SefService} from '../../../core/services/sef.service';
import {NgForOf, NgIf} from '@angular/common';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-l3',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './l3.component.html',
  standalone: true,
  styleUrl: './l3.component.scss'
})
export class L3Component implements OnInit{
  list: any[] = [];
  loading = false;

  constructor(private sefService: SefService) {}

  ngOnInit(): void {
    this.loading = true;
    this.sefService.getL3().subscribe({
      next: data => {
        this.list = data;
        this.loading = false;
      }
    });
  }
}
