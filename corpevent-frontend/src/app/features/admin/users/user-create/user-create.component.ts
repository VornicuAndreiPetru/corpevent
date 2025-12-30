import {Component, OnInit} from '@angular/core';
import {AdminUserService} from '../../../../core/services/admin-user.service';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule, NgForm} from '@angular/forms';

@Component({
  selector: 'app-user-create',
  imports: [
    NgIf,
    FormsModule
  ],
  templateUrl: './user-create.component.html',
  standalone: true,
  styleUrl: './user-create.component.scss'
})
export class UserCreateComponent{

  form: any = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    role: ''
  };

  success = '';
  error = '';

  constructor(private adminService: AdminUserService) {
  }
  create(f:NgForm){
    this.success = '';
    this.error = '';

    this.adminService.create(this.form).subscribe({
      next: () => {
        this.success = 'Utilizator creat cu succes';

        f.resetForm({
          username: '',
          password: '',
          firstname: '',
          lastname: '',
          email: '',
          role: ''
        });
      },
      error: err => {
        if (err.error && typeof err.error === 'object') {
          if (err.error.general) {
            this.error = err.error.general;
            return;
          }
          const firstKey = Object.keys(err.error)[0];
          this.error = err.error[firstKey];
          return;
        }
        this.error = 'Eroare la creare utilizator';
      }
    });
  }




}
