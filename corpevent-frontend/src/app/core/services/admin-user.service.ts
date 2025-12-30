import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminUserService {

  private base = 'http://localhost:8080/api/admin/users';
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<any[]>(this.base);
  }

  create(dto: any){
    return this.http.post<any>(this.base, dto);
  }

  assign(execId: number, sefId:number){
    return this.http.post(`${this.base}/organigrama?execId=${execId}&sefId=${sefId}`, {});
  }

  getHierarchy(){
    return this.http.get<any[]>(`${this.base}/organigrama`);
  }

  toggle(id: number) {
    return this.http.patch(`${this.base}/${id}/toggle`, {});
  }
}
