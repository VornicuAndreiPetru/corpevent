import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {PublicService} from '../../../core/services/public.service';
import {DatePipe, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-request',
  imports: [
    DatePipe,
    NgIf,
    FormsModule,
    RouterLink
  ],
  templateUrl: './request.component.html',
  standalone: true,
  styleUrl: './request.component.scss'
})
export class RequestComponent implements OnInit{

  eventId!:number;
  loading = true;
  event: any = null;

  requesterName = '';
  company = '';
  phone = '';
  email = '';
  clientStatus = '';

  message: string | null = null;
  errors: any = {};



  constructor(
    private route: ActivatedRoute,
    private publicService: PublicService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));

    this.publicService.getEvent(this.eventId).subscribe({
      next:data =>{
        this.event = data;
        this.loading = false;
      },
      error: err => {
        this.message = "Evenimentul nu a fost gasit.";
        this.loading = false;
      }
    });
  }



  submitRequest(){
    const dto = {
      eventId: this.eventId,
      requesterName: this.requesterName,
      company: this.company,
      clientStatus: this.clientStatus,
      phone: this.phone,
      email: this.email
    };

    this.publicService.createRequest(dto).subscribe({
      next: data =>{
        this.message = "Solicitarea a fost inregistrata! Verifica email-ul.";
      },
      error: err => {
        if(err.error){
          this.errors = err.error;
        }else{
          this.message = "Eroare la trimiterea solicitarii";
        }

      }
    });
  }

  back() {
    this.router.navigate(['']);
  }
}
