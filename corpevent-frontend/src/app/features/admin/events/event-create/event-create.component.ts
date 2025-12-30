import { Component } from '@angular/core';
import {AdminEventService} from '../../../../core/services/admin-event.service';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-event-create',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './event-create.component.html',
  standalone: true,
  styleUrl: './event-create.component.scss'
})
export class EventCreateComponent {

  form: any = {
    name: '',
    type: '',
    description: '',
    date: '',
    maxPlaces: ''
  }

  success = '';
  error = '';

  constructor(private eventService: AdminEventService) {
  }

  create(formRef: any){
    this.success = '';
    this.error = '';

    this.eventService.create(this.form).subscribe({
      next: () => {
        this.success = 'Eveniment creat cu succes';

        formRef.resetForm();

        this.form = {
          name: '',
          type: '',
          description: '',
          eventDate: '',
          maxPlaces: ''
        };
      },
      error: err => {

        if (err.error && typeof err.error === 'object') {
          const firstKey = Object.keys(err.error)[0];
          this.error = err.error[firstKey];
          return;
        }

        this.error = 'Eroare la creare eveniment';
      }
    });
  }

}
