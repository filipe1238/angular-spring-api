import { Component } from '@angular/core';
import { Carrinho } from './carrinho/carrinho';
import { CarrinhoService } from './carrinho/lista.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'front-app';

}
