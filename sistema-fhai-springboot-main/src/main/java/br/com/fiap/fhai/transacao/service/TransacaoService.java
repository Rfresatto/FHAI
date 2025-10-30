package br.com.fiap.fhai.transacao.service;

import br.com.fiap.fhai.transacao.model.Transacao;
import br.com.fiap.fhai.transacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao cadastrar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Transacao buscarPorId(int id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        if (transacao.isPresent()) {
            return transacao.get();
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public List<Transacao> buscarTodos() {
        return transacaoRepository.findAll();
    }

    public void excluir(int id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        if (transacao.isPresent()) {
            transacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Transacao atualizar(int id, Transacao transacao) {
        Optional<Transacao> transacaoAtual = transacaoRepository.findById(id);
        if (transacaoAtual.isPresent()) {
            return transacaoRepository.save(transacao);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
