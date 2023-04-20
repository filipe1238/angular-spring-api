import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProdutoService } from '../new-produto/produto.service';
import { CarrinhoService } from '../new-carrinho/carrinho.service';
import CustomStore from 'devextreme/data/custom_store';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-search-carrinho',
  templateUrl: './search-carrinho.component.html',
  styleUrls: ['./search-carrinho.component.scss'],
})
export class SearchCarrinhoComponent {
  dataSource: any;
  constructor(private service: CarrinhoService, private router: Router) {
    this.dataSource = new CustomStore({
      key: 'id',
      load: (loadOptions: any) => {
        console.log(loadOptions);
        return lastValueFrom(service.getCarrinhos()).catch((error) => {
          throw 'Data Loading Error';
        });
      },

   /*    update: (key, values) => {
        console.log(key, values);
        return lastValueFrom(service.updateProduto(key, values));
      }, */

      /*  insert: (values) => {
        return values;
      },
*/
      /* remove: (key) => {
        console.log(key);
        return lastValueFrom(service.deleteProduto(key));
      }, */
    });
  }

  redirectCreate = () => {
    this.router.navigateByUrl('/new-carrinho');
  };
}
