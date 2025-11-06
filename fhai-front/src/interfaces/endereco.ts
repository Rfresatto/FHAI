export interface TEndereco {
  id?: number | null;
  logradouro: string;
  nr_residencia: string;
  complemento?: string | null;
  cep: string;
  cidade: string;
  estado: string;
  referencia?: string | null;
  enderecoCompleto?: string | null;
}
