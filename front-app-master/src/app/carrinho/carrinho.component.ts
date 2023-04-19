import { Component, OnInit } from '@angular/core';
import { Carrinho } from './carrinho';
import { CarrinhoService } from './lista.service';
import { HttpErrorResponse } from '@angular/common/http';
import { v1 as uuid } from 'uuid';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  /*   template: `
  <h1>hello world</h1>
  `, */
  styleUrls: ['./carrinho.component.scss'],
})
export class CarrinhoComponent implements OnInit {
  public carrinhos: Carrinho[];
  public carrinhoTeste: Carrinho;
  public test: String = 'teste';

  async ngOnInit() {
    console.log(this.carrinhos);
    /* this.getCarrinho("3d69ec18-88ba-4ca7-abb9-2b5ac1b82b65"); */
    this.getCarrinhos();
  }

  constructor(private service: CarrinhoService) {}

  public getCarrinhos(): void {
    this.service.getCarrinhos().subscribe(
      (response: any) => {
        console.log(response);
        this.carrinhos = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public getCarrinho(id: uuid): void {
    this.service.getCarrinho(id).subscribe(
      (response: any) => {
        console.log(response);
        this.carrinhoTeste = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
