import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminEventService {

  private base = 'http://localhost:8080/api/admin/events';

  constructor(private http: HttpClient) { }

  list(){
    return this.http.get<any[]>(this.base);
  }

  create(dto: any){
    return this.http.post<any>(this.base, dto);
  }

  update(id:number, dto:any){
    return this.http.put<any>(`${this.base}/${id}`, dto);
  }

  delete(id:number){
    return this.http.delete(`${this.base}/${id}`);
  }

  stats() {
    return this.http.get<any[]>(`${this.base}/stats`);
  }
}
