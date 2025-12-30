import {Component, OnInit} from '@angular/core';
import {ExecService} from '../../../core/services/exec.service';
import {NgForOf, NgIf} from '@angular/common';
import {NavComponent} from '../../../core/components/nav/nav.component';

@Component({
  selector: 'app-l1',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './l1.component.html',
  standalone: true,
  styleUrl: './l1.component.scss'
})
export class L1Component implements OnInit{

  loading = true;
  requests: any[] = [];
  constructor(
    private execService: ExecService
  ) {
  }

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.execService.getL1().subscribe({
      next: data =>{
        this.requests = data;
        this.loading = false;
      },
      error: err =>{
        this.loading = false;
        console.error(err);
      }
    });
  }

  takeRequest(id:number){
    this.execService.takeRequest(id).subscribe({
      next: ()=>{
        this.load();
      },
      error: err => {
        alert("Eroare: " + err.error?.message);
      }
    });
  }

}
