import {Component, OnInit} from '@angular/core';
import {AdminUserService} from '../../../../core/services/admin-user.service';
import {NgClass, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-users-list',
  imports: [
    NgIf,
    NgForOf,
    NgClass
  ],
  templateUrl: './users-list.component.html',
  standalone: true,
  styleUrl: './users-list.component.scss'
})
export class UsersListComponent implements OnInit{

  users: any[] = [];
  loading = false;

  constructor(private adminService: AdminUserService) {
  }

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.loading = true;
    this.adminService.list().subscribe({
      next: res => {
        this.users = res;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  toggle(user: any) {
    const action = user.enabled ? 'dezactivezi' : 'activezi';

    if (!confirm(`Sigur vrei să ${action} acest cont?`)) return;

    this.adminService.toggle(user.id).subscribe({
      next: () => this.load(),
      error: () => alert('Eroare la modificarea statusului')
    });
  }



}
