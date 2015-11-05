package br.com.gelateria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gelateria.model.Colaborador;

@Repository
public interface ColaboradorDao extends Dao<Colaborador , Integer> {
    public String procurarPorNome(String nome);

	public List<Colaborador> pegarNome(String nome);
	
	@Transactional
	public Colaborador buscaLogin(String login);
}
