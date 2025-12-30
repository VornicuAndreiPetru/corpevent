import {Component, OnInit} from '@angular/core';
import {PublicService} from '../../../core/services/public.service';
import {Router} from '@angular/router';
import {CommonModule, DatePipe, NgFor, NgIf} from '@angular/common';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-events',
  standalone: true,
  imports: [CommonModule, NgIf, NgFor, DatePipe],
  templateUrl: './events.component.html',
  styleUrl: './events.component.scss'
})
export class EventsComponent implements OnInit{

  events: any[] = [];
  loading = true;

  constructor(
    private publicService: PublicService,
    private router: Router
  ) {
  }
  ngOnInit() {
    this.publicService.getEvents().subscribe({
      next:(data)=>{
        this.events = data;
        this.loading = false;
      },
      error:()=>{
        this.loading = false;
      }
    });
  }

  goToRequest(id:number){
    this.router.navigate(['/request', id]);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
