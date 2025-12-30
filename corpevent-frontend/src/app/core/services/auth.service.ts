import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

interface LoginResponse{
  token: string;
  username: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) {}

  getUser(){
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
  }

  hasRole(role: string){
    return this.getUser()?.roles?.includes(role) ?? false;
  }
  login(username: string, password: string): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(this.apiUrl, {username, password});
  }

  logout(){
    localStorage.clear();
    location.href = '/';
  }

  username(){
    return this.getUser()?.username;
  }

  isLoggedIn(): boolean{
    return !!localStorage.getItem('token');
  }

  getRoles(): string[]{
    return JSON.parse(localStorage.getItem('roles') || '[]');
  }
}
