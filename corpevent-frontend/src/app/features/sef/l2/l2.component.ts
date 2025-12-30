import {Component, OnInit} from '@angular/core';
import {SefService} from '../../../core/services/sef.service';
import {FormsModule} from '@angular/forms';
import {DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-l2',
  imports: [
    FormsModule,
    NgIf,
    NgForOf,
    DatePipe
  ],
  templateUrl: './l2.component.html',
  standalone: true,
  styleUrl: './l2.component.scss'
})
export class L2SefComponent implements OnInit{

  requests: any[] = [];
  loading = false;
  error: string | null = null;
  comments: {[id:number]:string} = {};

  constructor(private sefService: SefService) {
  }
  ngOnInit(): void {
    this.load();
  }


  load(){
    this.loading = true;
    this.sefService.getL2().subscribe({
      next: data =>{
        this.requests = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        this.error = "Eroare la incarcarea cererilor din L2"
      }
    });
  }

  decide(id:number, decision: 'POZITIV' | 'NEGATIV'){
    const comment = this.comments[id] || '';
    this.sefService.finalDecision(id,decision,comment).subscribe({
      next: () => this.load(),
      error: () => alert('Eroare la trimiterea avizului final')
    });
  }
}
