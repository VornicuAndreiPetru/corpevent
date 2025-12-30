import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavComponent} from '../../core/components/nav/nav.component';

@Component({
  selector: 'app-admin-layout',
  imports: [
    RouterOutlet,
    NavComponent
  ],
  templateUrl: './admin-layout.component.html',
  standalone: true,
  styleUrl: './admin-layout.component.scss'
})
export class AdminLayoutComponent {

}
