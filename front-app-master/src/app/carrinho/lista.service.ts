import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Carrinho } from './carrinho';
import { environment } from 'src/environments/environment';
import { v1 as uuid } from 'uuid';

@Injectable({providedIn: 'root'})
export class CarrinhoService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getCarrinhos(): Observable<Carrinho[]> {
    return this.http.get<Carrinho[]>(`${this.apiServerUrl}carrinhos`);
  }
  public getCarrinho(id: uuid): Observable<Carrinho[]> {
    return this.http.get<Carrinho[]>(`${this.apiServerUrl}carrinhos/${id}`);
  }

  public addEmployee(employee: Carrinho): Observable<Carrinho> {
    return this.http.post<Carrinho>(`${this.apiServerUrl}employee/add`, employee);
  }

  public updateEmployee(employee: Carrinho): Observable<Carrinho> {
    return this.http.put<Carrinho>(`${this.apiServerUrl}employee/update`, employee);
  }

  public deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}employee/delete/${employeeId}`);
  }
}
