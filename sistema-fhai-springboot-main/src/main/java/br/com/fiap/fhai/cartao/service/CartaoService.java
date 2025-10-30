package br.com.fiap.fhai.cartao.service;

import br.com.fiap.fhai.cartao.model.Cartao;
import br.com.fiap.fhai.cartao.repository.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public Cartao cadastrar(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public Cartao buscarPorId(int id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
    }

    public List<Cartao> buscarTodos() {
        return cartaoRepository.findAll();
    }

    public void excluir(int id) {
        if (!cartaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }
        cartaoRepository.deleteById(id);
    }

    public Cartao atualizar(int id, Cartao cartaoAtualizado) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        cartao.setNm_cartao(cartaoAtualizado.getNm_cartao());
        cartao.setNr_cartao(cartaoAtualizado.getNr_cartao());
        cartao.setTp_cartao(cartaoAtualizado.getTp_cartao());
        cartao.setNm_titular(cartaoAtualizado.getNm_titular());
        cartao.setDt_validade(cartaoAtualizado.getDt_validade());
        cartao.setVl_saldo(cartaoAtualizado.getVl_saldo());
        cartao.setContaBancaria(cartaoAtualizado.getContaBancaria());

        return cartaoRepository.save(cartao);
    }
}