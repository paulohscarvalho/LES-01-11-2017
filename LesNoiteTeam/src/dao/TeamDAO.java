package dao;

import java.util.List;

import entidade.Team;

public interface TeamDAO {
	
	void adicionar(Team t);
	List<Team> pesquisar(String nome);

}
