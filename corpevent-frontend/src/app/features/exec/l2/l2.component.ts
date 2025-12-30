import {Component, OnInit} from '@angular/core';
import {ExecService} from '../../../core/services/exec.service';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-l2',
  imports: [
    NgIf,
    NgForOf,
    FormsModule,
    DatePipe
  ],
  templateUrl: './l2.component.html',
  standalone: true,
  styleUrl: './l2.component.scss'
})
export class L2ExecComponent implements OnInit{

  requests: any[] = [];
  loading = false;
  error: string | null = null;

  comments: {[id:number]: string} = {};

  constructor(private execService: ExecService) {
  }
  ngOnInit(): void {
    this.load();
  }

  load(){
    this.loading = true;
    this.error = null;
    this.execService.getL2().subscribe({
      next: data => {
        this.requests = data;
        this.loading = false;
      },
      error: err =>{
        console.error(err);
        this.error = 'Eroare la incarcarea solicitarilor din L2';
        this.loading = false;
      }
    });
  }

  decide(reqId:number, decision: 'POZITIV' | 'NEGATIV'){
    const comment = this.comments[reqId] || '';
    this.execService.preliminaryDecision(reqId, decision, comment).subscribe({
      next: ()=>{
        this.load();
      },
      error: err =>{
        console.error(err);
        alert("Eroare la trimiterea avizului preliminar");
      }
    });
  }

  isDirectFinal(r:any): boolean{
    return r.clientStatus === 'CLIENT_EXISTENT' && r.event?.type === 'ACTUALIZARI_MAJORE';
  }

}
