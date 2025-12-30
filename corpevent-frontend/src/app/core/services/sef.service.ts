import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';


const API = 'http://localhost:8080/api/internal/sef';

@Injectable({
  providedIn: 'root'
})
export class SefService {


  constructor(private http: HttpClient) { }

  getL2(): Observable<any[]>{
    return this.http.get<any[]>(`${API}/requests/l2`);
  }

  finalDecision(id: number, decision: 'POZITIV' | 'NEGATIV', comment: string): Observable<any>{
    const params = new HttpParams()
      .set('decision', decision)
      .set('comment', comment);

    return this.http.post(`${API}/requests/${id}/final`,null,{params});
  }

  getL3(): Observable<any[]>{
    return this.http.get<any[]>(`${API}/requests/l3`);
  }

  getL4(): Observable<any[]>{
    return this.http.get<any[]>(`${API}/requests/l4`);
  }

  getMyOrganigram(){
    return this.http.get<any[]>(`${API}/organigrama`);
  }
}
