import { Component, OnInit, ViewChild } from '@angular/core';
import { Carrinho } from '../../interfaces/carrinho';
import { Router } from '@angular/router';
import { CarrinhoService } from './carrinho.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ProdutoService } from '../new-produto/produto.service';
import { Produto } from 'src/app/interfaces/Produto';
import DataGrid from 'devextreme/ui/data_grid';
import { DxDataGridComponent, DxTagBoxComponent } from 'devextreme-angular';

@Component({
  selector: 'app-new-carrinho',
  templateUrl: './new-carrinho.component.html',
  styleUrls: ['./new-carrinho.component.scss'],
})
export class NewCarrinhoComponent implements OnInit {
  @ViewChild('targetDataGrid') prodrodutosSelec: any;
  constructor(
    private service: CarrinhoService,
    private prodService: ProdutoService,
    private router: Router
  ) {}

  public carrinho: Carrinho = {
    descricao: '',
  };
  produtos: Produto[] = [];

  ngOnInit(): void {
    this.prodService.getProdutos().subscribe((res) => {
      this.produtos = res;
    });
  }

  initCarrinho = () => {
    this.carrinho = {
      descricao: '',
    };
  };

  cancelar = () => {
    this.carrinho = {
      descricao: '',
    };
  };
  click = () => {
    console.log(this.carrinho.descricao);
  };
  onFormSubmit = (event: Event) => {
    event.preventDefault();
    console.log(this.prodrodutosSelec.selectedItems);
    /*     this.saveProduto(); */
  };
  public saveProduto(): void {
    this.service.addCarrinho(this.carrinho).subscribe(
      (response: Carrinho) => {
        console.log(response);
        this.initCarrinho();
        this.router.navigateByUrl('/search-carrinho');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  onValueChanged = (e: Event) => {
    e.target;
    /* const value = e.component.option('value');
    const selectedItems = e.component.option('selectedItems'); */
  };
}
