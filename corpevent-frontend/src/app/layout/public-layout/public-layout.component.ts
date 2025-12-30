import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-public-layout',
  imports: [
    RouterOutlet
  ],
  templateUrl: './public-layout.component.html',
  standalone: true,
  styleUrl: './public-layout.component.scss'
})
export class PublicLayoutComponent {

}
