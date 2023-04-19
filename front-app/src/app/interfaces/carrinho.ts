import { Produto } from "./Produto";

export interface Carrinho {
  id?:any,
  descricao: string;
  produtos?: Produto[]
}
