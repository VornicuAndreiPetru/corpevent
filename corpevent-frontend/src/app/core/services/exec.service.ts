import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExecService {

  private api ='http://localhost:8080/api/internal/exec';

  constructor(private http: HttpClient) { }

  getL1(){
    return this.http.get<any[]>(`${this.api}/requests/l1`);
  }

  takeRequest(id:number){
    return this.http.post(`${this.api}/requests/${id}/take`,{});
  }

  getL2(){
    return this.http.get<any[]>(`${this.api}/requests/l2`)
  }
  preliminaryDecision(id: number, decision: 'POZITIV' | 'NEGATIV', comment: string): Observable<any> {
    const params = new HttpParams()
      .set('decision', decision)
      .set('comment', comment);

    return this.http.post<any>(`${this.api}/requests/${id}/preliminary`, null, { params });
  }
}
