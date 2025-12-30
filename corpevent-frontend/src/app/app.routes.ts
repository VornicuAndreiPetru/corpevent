import { Routes } from '@angular/router';
import {EventsComponent} from './features/public/events/events.component';
import {RequestComponent} from './features/public/request/request.component';
import {LoginComponent} from './features/auth/login/login.component';

import {L1Component} from './features/exec/l1/l1.component';
import {L2ExecComponent} from './features/exec/l2/l2.component';
import {authGuard} from './core/guards/auth.guard';
import {L2SefComponent} from './features/sef/l2/l2.component';
import {L3Component} from './features/sef/l3/l3.component';
import {L4Component} from './features/sef/l4/l4.component';
import {PublicLayoutComponent} from './layout/public-layout/public-layout.component';
import {AdminLayoutComponent} from './layout/admin-layout/admin-layout.component';
import {ExecLayoutComponent} from './layout/exec-layout/exec-layout.component';
import {SefLayoutComponent} from './layout/sef-layout/sef-layout.component';
import {UserCreateComponent} from './features/admin/users/user-create/user-create.component';
import {UsersListComponent} from './features/admin/users/users-list/users-list.component';
import {UserOrganigramComponent} from './features/admin/users/user-organigram/user-organigram.component';
import {EventCreateComponent} from './features/admin/events/event-create/event-create.component';
import {EventListComponent} from './features/admin/events/event-list/event-list.component';
import {EventStatsComponent} from './features/admin/events/event-stats/event-stats.component';
import {SefOrganigramaComponent} from './features/sef/sef-organigrama/sef-organigrama.component';


export const routes: Routes = [


  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: '', component: EventsComponent },
      { path: 'request/:id', component: RequestComponent }
    ]
  },

  { path: 'login', component: LoginComponent },


  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    children: [

      {
        path: 'users',
        children: [
          { path: 'create', component: UserCreateComponent },
          { path: 'list', component: UsersListComponent },
          { path: 'organigrama', component: UserOrganigramComponent },
          { path: '', redirectTo: 'list', pathMatch: 'full' }
        ]
      },


      {
        path: 'events',
        children: [
          { path: 'create', component: EventCreateComponent },
          { path: 'list', component: EventListComponent },
          { path: 'stats', component: EventStatsComponent },
          { path: '', redirectTo: 'list', pathMatch: 'full' }
        ]
      },

      /* default admin */
      { path: '', redirectTo: 'users', pathMatch: 'full' }
    ]
  },

  {
    path: 'events',
    component: SefLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'stats', component: EventStatsComponent }
    ]
  },




  {
    path: 'exec',
    component: ExecLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'l1', component: L1Component },
      { path: 'l2', component: L2ExecComponent },
      { path: '', redirectTo: 'l1', pathMatch: 'full' }
    ]
  },


  {
    path: 'sef',
    component: SefLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'l2', component: L2SefComponent },
      { path: 'l3', component: L3Component },
      { path: 'l4', component: L4Component },
      { path: 'organigrama', component: SefOrganigramaComponent },
      { path: '', redirectTo: 'l2', pathMatch: 'full' }
    ]
  },



  { path: '**', redirectTo: 'login' }



];
