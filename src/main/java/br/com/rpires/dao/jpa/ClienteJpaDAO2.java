/**
 * 
 */
package br.com.rpires.dao.jpa;

import br.com.rpires.dao.generic.jpa.GenericDAO2;
import br.com.rpires.domain.jpa.Cliente1;
import br.com.rpires.domain.jpa.Cliente2;


public class ClienteJpaDAO2 extends GenericDAO2<Cliente2, Long> implements IClienteJpaDAO<Cliente2>{

	public ClienteJpaDAO2() {
		super(Cliente2.class);
	}

}
