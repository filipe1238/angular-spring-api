import { Component } from '@angular/core';
import { Carrinho } from '../../interfaces/carrinho';

@Component({
  selector: 'app-new-carrinho',
  templateUrl: './new-carrinho.component.html',
  styleUrls: ['./new-carrinho.component.scss'],
})
export class NewCarrinhoComponent {
  public carrinho: Carrinho = {
    descricao:""
  };


  cancelar = () =>{
    this.carrinho = {
      descricao:""
    };
  }
  click = () => {
    console.log(this.carrinho.descricao);
  };
  onFormSubmit = (event:Event) =>{
/*     event.preventDefault();
 */    console.log(event);

  }
}
