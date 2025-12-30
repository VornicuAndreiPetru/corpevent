import {Component, OnInit} from '@angular/core';
import {AdminUserService} from '../../../../core/services/admin-user.service';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-user-organigram',
  imports: [
    FormsModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './user-organigram.component.html',
  standalone: true,
  styleUrl: './user-organigram.component.scss'
})
export class UserOrganigramComponent implements OnInit{


  model = { execId: '', sefId: '' };
  message = '';
  error = '';

  tree: any[] = [];
  loading = false;

  constructor(private adminService: AdminUserService) {}

  ngOnInit(): void {
    this.loadTree();
  }

  assign() {
    this.message = '';
    this.error = '';

    this.adminService.assign(+this.model.execId, +this.model.sefId).subscribe({
      next: () => {
        this.message = 'Asignare realizată cu succes';
        this.loadTree();
      },
      error: () => this.error = 'Eroare la asignare'
    });
  }

  loadTree() {
    this.loading = true;

    this.adminService.getHierarchy().subscribe({
      next: relations => {
        this.tree = this.groupBySef(relations);
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  private groupBySef(relations: any[]) {
    const map = new Map<number, any>();

    relations.forEach(r => {
      const sef = r.sef;
      const exec = r.exec;

      if (!map.has(sef.id)) {
        map.set(sef.id, {
          sef,
          execs: []
        });
      }

      map.get(sef.id).execs.push(exec);
    });

    return Array.from(map.values());
  }


}
