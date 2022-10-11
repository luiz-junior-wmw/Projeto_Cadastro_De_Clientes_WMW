package br.com.wmw.projeto_integracao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wmw.projeto_integracao.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "Select c from Cliente c where upper(trim(c.nome)) like %?1%")
	List<Cliente> buscarPorNome (String nome);
	
	@Query(value = "Select c from Cliente c where upper(trim(c.nome)) like %?1% and status = 'WEB'")
	List<Cliente> buscarPorStatusClienteAtivo (String status);
	
	@Query(value = "Select c from Cliente c where status = 'WEB'")
	List<Cliente> buscarPorStatusClienteAtivo_Test (String status);
	
	@Query(value = "Select c from Cliente c where upper(trim(c.nome)) like %?1% and status = 'EXC_APP'")
	List<Cliente> buscarPorStatusClienteInativo (String status);
	
	@Query(value = "Select c from Cliente c where status = 'EXC_APP'")
	List<Cliente> buscarPorStatusClienteInativo_Test (String status);

	List<Cliente> findAllByCod(Long cod);
	
	

	
	
}
