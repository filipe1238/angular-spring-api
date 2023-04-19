import { Component } from '@angular/core';
import 'devextreme/data/odata/store';
import { Router } from '@angular/router';
import CustomStore from 'devextreme/data/custom_store';
import { ProdutoService } from '../new-produto/produto.service';
import DataSource from 'devextreme/data/data_source';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-search-produto',
  templateUrl: './search-produto.component.html',
  styleUrls: ['./search-produto.component.scss'],
})
export class SearchProdutoComponent {
  dataSource: any;

  constructor(private service: ProdutoService, private router: Router) {
    this.dataSource = new CustomStore({
      key: 'id',
      load: (loadOptions: any) => {
        console.log(loadOptions);
        return lastValueFrom(service.getProdutos())
          .catch((error) => {
            throw 'Data Loading Error';
          });
      },

      update: (key, values) => {
        console.log(key, values);
        return lastValueFrom(service.updateProduto(key, values));
      },

     /*  insert: (values) => {
        return values;
      },

      remove: (key) => {
        console.log(key);
        return key;
      }, */
    });
  }

  redirectCreate = () => {
    this.router.navigateByUrl('/new-produto');
  };
}
