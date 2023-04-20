import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { Carrinho } from 'src/app/interfaces/carrinho';


@Injectable({providedIn: 'root'})
export class CarrinhoService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getCarrinhos(): Observable<Carrinho[]> {
    return this.http.get<Carrinho[]>(`${this.apiServerUrl}carrinhos`);
  }
  public getCarrById(id: any): Observable<Carrinho[]> {
    return this.http.get<Carrinho[]>(`${this.apiServerUrl}carrinhos/${id}`);
  }

  public addCarrinho(carrinho: Carrinho): Observable<Carrinho> {
    return this.http.post<Carrinho>(`${this.apiServerUrl}carrinhos`, carrinho);
  }

  public updateCarrinho(id: any, carrinho: Carrinho): Observable<Carrinho> {
    carrinho.id=id;
    return this.http.put<Carrinho>(`${this.apiServerUrl}carrinhos/${id}`, carrinho);
  }

  public deleteCarrinho(id: any): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}carrinhos/${id}`);
  }
}
