import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../core/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string='';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }


  login() {
    this.errorMessage = '';

    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {

        localStorage.setItem('token', res.token);

        localStorage.setItem('user', JSON.stringify({
          username: res.username,
          roles: res.roles
        }));

        if (res.roles.includes('ADMIN_EVENT')) {
          this.router.navigate(['/admin/events']);
        } else if (res.roles.includes('ADMIN_USER')) {
          this.router.navigate(['/admin/users']);
        } else if (res.roles.includes('VALIDATOR_EXEC')) {
          this.router.navigate(['/exec/l1']);
        } else if (res.roles.includes('VALIDATOR_SEF')) {
          this.router.navigate(['/sef/l2']);
        } else {
          this.router.navigate(['/']);
        }
      },
      error: () => {
        this.errorMessage = 'Username sau parola incorecta';
      }
    });
  }

  back() {
    this.router.navigate(['']);
  }
}
