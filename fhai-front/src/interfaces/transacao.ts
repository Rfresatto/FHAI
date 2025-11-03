export interface ITransacao {
  id?: number;
  nm_transacao: string;
  ds_transacao: string;
  vl_transacao: number;
  tp_transacao: string;
  dt_transacao: string;
  cartao?: null;
}
