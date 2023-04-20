import { Produto } from "./produto";

export interface Carrinho {
  id?:any,
  descricao: string;
  produtos?: Produto[]
}
