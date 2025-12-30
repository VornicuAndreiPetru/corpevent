import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PublicService {

  private api = 'http://localhost:8080/api/public';

  constructor(private http: HttpClient) { }

  getEvents(){
    return this.http.get<any[]>(`${this.api}/events`);
  }

  getEvent(id: number){
    return this.http.get<any>(`${this.api}/events/${id}`)
  }

  createRequest(dto: any){
    return this.http.post<any>(`${this.api}/requests`, dto);
  }
}
