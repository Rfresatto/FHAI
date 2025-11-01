interface TUsuarios {
  id: number;
  nome: string;
  senha: number;
  sexo: string;
  email: string;
  contato: number;
  endereco: TEndereco | null;
  contaBancaria: TContaBancaria | null;
}

interface TContaBancaria {
  id: number;
  numeroConta: number;
  nomeBanco: string;
  tipoConta: string;
  agencia: number;
  saldo: number;
} 

interface TEndereco {
    id: number;
    logradouro: string;
    nr_residencia: number;
    complemento: null;
    cep: number;
    cidade: string;
    estado: string;
    referencia: string | null;
    enderecoCompleto: string;
}