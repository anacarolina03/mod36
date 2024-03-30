/**
 * 
 */
package br.com.rpires.dao.jpa;

import br.com.rpires.dao.generic.jpa.GenericDAO1;
import br.com.rpires.dao.generic.jpa.GenericJpaDAO;
import br.com.rpires.domain.jpa.Cliente1;


public class ClienteJpaDAO1 extends GenericDAO1<Cliente1, Long> implements IClienteJpaDAO<Cliente1> {

	public ClienteJpaDAO1() {
		super(Cliente1.class);
	}

}
