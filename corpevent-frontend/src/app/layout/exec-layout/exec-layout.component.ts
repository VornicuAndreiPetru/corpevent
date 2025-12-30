import { Component } from '@angular/core';
import {NavComponent} from '../../core/components/nav/nav.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-exec-layout',
  imports: [
    NavComponent,
    RouterOutlet
  ],
  templateUrl: './exec-layout.component.html',
  standalone: true,
  styleUrl: './exec-layout.component.scss'
})
export class ExecLayoutComponent {

}
