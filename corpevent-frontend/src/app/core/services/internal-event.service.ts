import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InternalEventService {

  private baseUrl = 'http://localhost:8080/api/internal/events';

  constructor(private http: HttpClient) { }

  listWithStats(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
}
