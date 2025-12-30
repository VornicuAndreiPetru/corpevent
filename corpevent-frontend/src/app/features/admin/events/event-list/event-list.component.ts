import {Component, OnInit} from '@angular/core';
import {AdminEventService} from '../../../../core/services/admin-event.service';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-event-list',
  imports: [
    NgForOf,
    DatePipe,
    NgIf,
    FormsModule
  ],
  templateUrl: './event-list.component.html',
  standalone: true,
  styleUrl: './event-list.component.scss'
})
export class EventListComponent implements OnInit{


  events: any[] = [];
  loading = false;
  showForm = false;
  editing = false;

  form: any = {
    id: null,
    name: '',
    description: '',
    type: '',
    eventDate: '',
    maxPlaces: ''
  };

  constructor(private eventService: AdminEventService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.loading = true;
    this.eventService.list().subscribe({
      next: res => {
        this.events = res;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  edit(event: any) {
    this.showForm = true;
    this.editing = true;

    this.form = {
      id: event.id,
      name: event.name,
      description: event.description,
      type: event.type,
      eventDate: this.toDatetimeLocal(event.eventDate), // ✅ FIX DUBLU
      maxPlaces: event.maxPlaces
    };
  }


  private toDatetimeLocal(date: string): string {
    if (!date) return '';
    return date.replace(' ', 'T').substring(0, 16);
  }






  openCreate() {
    this.showForm = true;
    this.editing = false;
    this.resetForm();
  }

  cancel() {
    this.showForm = false;
    this.editing = false;
    this.resetForm();
  }

  save() {
    if (this.editing) {
      this.eventService.update(this.form.id, this.form)
        .subscribe(() => {
          this.load();
          this.cancel();
        });
    } else {
      this.eventService.create(this.form)
        .subscribe(() => {
          this.load();
          this.cancel();
        });
    }
  }

  delete(id: number) {
    if (!confirm('Sigur ștergi evenimentul?')) return;

    this.eventService.delete(id)
      .subscribe(() => this.load());
  }

  private resetForm() {
    this.form = {
      id: null,
      name: '',
      description: '',
      type: '',
      date: '',
      maxPlaces: 0
    };
  }

}
