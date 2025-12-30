import {Component, OnInit} from '@angular/core';
import {SefService} from '../../../core/services/sef.service';
import {NgForOf, NgIf} from '@angular/common';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-l4',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './l4.component.html',
  standalone: true,
  styleUrl: './l4.component.scss'
})
export class L4Component implements OnInit{

  list: any[] = [];
  loading = false;

  constructor(private sefService: SefService) {}

  ngOnInit(): void {
    this.loading = true;
    this.sefService.getL4().subscribe({
      next: data => {
        this.list = data;
        this.loading = false;
      }
    });
  }

}
