import { Component, OnInit, SimpleChanges } from '@angular/core';
import { Produto } from 'src/app/interfaces/produto';
import { ProdutoService } from './produto.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-produto',
  templateUrl: './new-produto.component.html',
  styleUrls: ['./new-produto.component.scss'],
})
export class NewProdutoComponent implements OnInit {
  public produto: Produto = {
    descricao: '',
    vrBruto: 0.0,
    vrDesc: 0.0,
    vrLiq: 0.0,
  };
  initProd = () => {
    this.produto = {
      descricao: '',
      vrBruto: 0.0,
      vrDesc: 0.0,
      vrLiq: 0.0,
    };
  };
  constructor(private service: ProdutoService, private router: Router) {}

  ngOnInit(): void {
    this.initProd();
  }
  ngOnChanges(changes: SimpleChanges) {
    // changes.prop contains the old and the new value...
  }
  cancelar = () => {
    this.initProd();
  };

  onChangeCalc = () => {
    if (this.produto.vrDesc > this.produto.vrBruto) {
      this.produto.vrDesc = this.produto.vrBruto;
    }
    this.produto.vrLiq = this.produto.vrBruto - this.produto.vrDesc;
  };

  voltar = () => {
    this.router.navigateByUrl('/search-produto');
  };
  onFormSubmit = (event: Event) => {
    event.preventDefault();
    this.saveProduto();
  };

  public saveProduto(): void {
    this.service.addProduto(this.produto).subscribe(
      (response: Produto) => {
        console.log(response);
        this.initProd();
        this.router.navigateByUrl('/search-produto');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
