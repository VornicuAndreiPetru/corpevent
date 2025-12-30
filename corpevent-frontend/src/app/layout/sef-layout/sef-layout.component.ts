import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavComponent} from '../../core/components/nav/nav.component';

@Component({
  selector: 'app-sef-layout',
  imports: [
    RouterOutlet,
    NavComponent
  ],
  templateUrl: './sef-layout.component.html',
  standalone: true,
  styleUrl: './sef-layout.component.scss'
})
export class SefLayoutComponent {

}
