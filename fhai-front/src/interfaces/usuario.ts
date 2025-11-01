export interface TUsuarios {
    id: number
    nome: string
    senha: number
    sexo: string,
    email: string
    contato: number,
    endereco: {
        id: number,
        logradouro: string,
        nr_residencia:number,
        complemento: null,
        cep: number,
        cidade: string,
        estado: string,
        referencia: string | null,
        enderecoCompleto: string
    } | null,
    contaBancaria: {
        id: number,
        numeroConta: number,
        nomeBanco: string,
        tipoConta: string,
        agencia: number,
        saldo: number
    } | null
}