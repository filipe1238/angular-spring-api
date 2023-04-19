import { Component, OnInit } from '@angular/core';
import { Carrinho } from './carrinho';
import { CarrinhoService } from './lista.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
/*   template: `
  <h1>hello world</h1>
  `, */
  styleUrls: ['./carrinho.component.scss']
})
export class CarrinhoComponent implements OnInit {


  ngOnInit(): void {
  }
  public carrinhos: Carrinho[];

  constructor(private service: CarrinhoService) {}

  public getCarrinho(): void {
    this.service.getCarrinhos().subscribe(
      (response: Carrinho[]) => {
        this.carrinhos = response;
        console.log(this.carrinhos);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
