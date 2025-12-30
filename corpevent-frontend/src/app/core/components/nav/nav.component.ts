import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-nav',
  imports: [CommonModule, RouterModule],
  templateUrl: './nav.component.html',
  standalone: true,
  styleUrl: './nav.component.scss'
})
export class NavComponent {

  auth = inject(AuthService);

  logout(){
    this.auth.logout();
  }

}
