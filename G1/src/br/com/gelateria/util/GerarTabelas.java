package br.com.gelateria.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerarTabelas {
	
	/*para verificar erros  gravação da tabela*/
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistema");
		factory.close();
	}

}
