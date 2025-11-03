package br.com.fiap.fhai.transacao.service;

import br.com.fiap.fhai.transacao.model.Transacao;
import br.com.fiap.fhai.transacao.repository.TransacaoRepository;
import br.com.fiap.fhai.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Transacao cadastrar(Long idUsuario, Transacao transacao) {
        return usuarioRepository.findById(Math.toIntExact(idUsuario))
                .map(usuario -> {
                    transacao.setUsuario(usuario);
                    return transacaoRepository.save(transacao);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public Transacao buscarPorId(Long idUsuario, Long idTransacao) {
        return transacaoRepository.findByIdAndUsuario_Id(Math.toIntExact(idTransacao), Math.toIntExact(idUsuario))
                .orElseThrow(() -> new RuntimeException("Transação não pertence ao usuário"));
    }

    public List<Transacao> buscarTodosPorUsuario(Long idUsuario) {
        return transacaoRepository.findByUsuario_Id(Math.toIntExact(idUsuario));
    }

    public void excluir(Long idUsuario, Long idTransacao) {
        Transacao transacao = buscarPorId(idUsuario, idTransacao);
        transacaoRepository.delete(transacao);
    }

    public Transacao atualizar(Long idUsuario, Long idTransacao, Transacao dados) {
        return transacaoRepository.findByIdAndUsuario_Id(Math.toIntExact(idTransacao), Math.toIntExact(idUsuario))
                .map(transacao -> {
                    transacao.setNm_transacao(dados.getNm_transacao());
                    transacao.setDs_transacao(dados.getDs_transacao());
                    transacao.setVl_transacao(dados.getVl_transacao());
                    transacao.setTp_transacao(dados.getTp_transacao());
                    transacao.setDt_transacao(dados.getDt_transacao());
                    return transacaoRepository.save(transacao);
                })
                .orElseThrow(() -> new RuntimeException("Transação não encontrada para este usuário"));
    }
}