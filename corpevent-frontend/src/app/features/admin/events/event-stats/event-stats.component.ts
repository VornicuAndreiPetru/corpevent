import {Component, OnInit} from '@angular/core';
import {AdminEventService} from '../../../../core/services/admin-event.service';
import {NgForOf, NgIf} from '@angular/common';
import {InternalEventService} from '../../../../core/services/internal-event.service';

@Component({
  selector: 'app-event-stats',
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './event-stats.component.html',
  standalone: true,
  styleUrl: './event-stats.component.scss'
})
export class EventStatsComponent implements OnInit{

  stats: any[] = [];
  loading = false;

  constructor(private eventService: InternalEventService) {
  }
  ngOnInit() {
    this.load();
  }

  load(){
    this.loading = true;
    this.eventService.listWithStats().subscribe({
      next: res =>{
        this.stats = res;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

}
