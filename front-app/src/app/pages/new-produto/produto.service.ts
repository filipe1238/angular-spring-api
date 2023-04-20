import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto } from 'src/app/interfaces/Produto';
import { environment } from 'src/app/environments/environment';


@Injectable({providedIn: 'root'})
export class ProdutoService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getProdutos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${this.apiServerUrl}prods`);
  }
  public getProdutoById(id: any): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${this.apiServerUrl}prods/${id}`);
  }

  public addProduto(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(`${this.apiServerUrl}prods`, produto);
  }

  public updateProduto(id: any, prod: Produto): Observable<Produto> {
    prod.id=id;
    return this.http.put<Produto>(`${this.apiServerUrl}prods/${id}`, prod);
  }

  public deleteProduto(id: any): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}prods/${id}`);
  }
}
