package br.com.rpires.dao;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import br.com.rpires.dao.jpa.ClienteJpaDAO1;
import br.com.rpires.dao.jpa.ClienteJpaDAO2;
import br.com.rpires.domain.jpa.Cliente1;
import br.com.rpires.domain.jpa.Cliente2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.rpires.dao.jpa.IClienteJpaDAO;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteDAOTest {

    private IClienteJpaDAO<Cliente1> clienteDAO1;

    private IClienteJpaDAO<Cliente2> clienteDAO2;

    private Random rd;

    public ClienteDAOTest() {
        clienteDAO1 = new ClienteJpaDAO1();
        clienteDAO2 = new ClienteJpaDAO2();
        rd = new Random();
    }



   @After
    public void end() throws DAOException {
        Collection<Cliente1> list1 = clienteDAO1.buscarTodos();
        excluir1(list1);

        Collection<Cliente2> list2 = clienteDAO2.buscarTodos();
        excluir2(list2);
    }

    private void excluir1(Collection<Cliente1> list) {
        list.forEach(cli -> {
            try {
                clienteDAO1.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluir2(Collection<Cliente2> list) {
        list.forEach(cli -> {
            try {
                clienteDAO2.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Cliente1 cliente1 = criarCliente();
        clienteDAO1.cadastrar(cliente1);

        Cliente1 clienteConsultado = clienteDAO1.consultar(cliente1.getId());
        Assert.assertNotNull(clienteConsultado);

        Cliente2 cliente2 = criarCliente2();
        clienteDAO2.cadastrar(cliente2);

        Cliente2 clienteConsultado2 = clienteDAO2.consultar(cliente2.getId());
        Assert.assertNotNull(clienteConsultado2);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente1 cliente1 = criarCliente();
        Cliente1 retorno1 = clienteDAO1.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Cliente1 clienteConsultado1 = clienteDAO1.consultar(retorno1.getId());
        Assert.assertNotNull(clienteConsultado1);

        clienteDAO1.excluir(cliente1);

        Cliente2 cliente2 = criarCliente2();
        Cliente2 retorno2 = clienteDAO2.cadastrar(cliente2);
        Assert.assertNotNull(retorno2);

        Cliente2 clienteConsultado2 = clienteDAO2.consultar(retorno2.getId());
        Assert.assertNotNull(clienteConsultado2);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente1 cliente1 = criarCliente();
        Cliente1 retorno1 = clienteDAO1.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Cliente1 clienteConsultado1 = clienteDAO1.consultar(cliente1.getId());
        Assert.assertNotNull(clienteConsultado1);

        clienteDAO1.excluir(cliente1);

        Cliente2 cliente2 = criarCliente2();
        Cliente2 retorno2 = clienteDAO2.cadastrar(cliente2);
        Assert.assertNotNull(retorno2);

        Cliente2 clienteConsultado2 = clienteDAO2.consultar(cliente2.getId());
        Assert.assertNotNull(clienteConsultado2);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente1 cliente1 = criarCliente();
        Cliente1 retorno1 = clienteDAO1.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Cliente1 clienteConsultado1 = clienteDAO1.consultar(cliente1.getId());
        Assert.assertNotNull(clienteConsultado1);

        clienteConsultado1.setNome("Rodrigo Pires");
        clienteDAO1.alterar(clienteConsultado1);

        Cliente1 clienteAlterado1 = clienteDAO1.consultar(clienteConsultado1.getId());
        Assert.assertNotNull(clienteAlterado1);
        Assert.assertEquals("Rodrigo Pires", clienteAlterado1.getNome());

        clienteDAO1.excluir(cliente1);
        //

        Cliente2 cliente2 = criarCliente2();
        Cliente2 retorno2 = clienteDAO2.cadastrar(cliente2);
        Assert.assertNotNull(retorno2);

        Cliente2 clienteConsultado2 = clienteDAO2.consultar(cliente2.getId());
        Assert.assertNotNull(clienteConsultado2);

        clienteConsultado2.setNome("Matteo");
        clienteDAO2.alterar(clienteConsultado2);

        Cliente2 clienteAlterado2 = clienteDAO2.consultar(clienteConsultado2.getId());
        Assert.assertNotNull(clienteAlterado2);
        Assert.assertEquals("Matteo", clienteAlterado2.getNome());
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente1 cliente1 = criarCliente();
        Cliente1 retorno1 = clienteDAO1.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Cliente2 cliente2 = criarCliente2();
        Cliente2 retorno2 = clienteDAO2.cadastrar(cliente2);
        Assert.assertNotNull(retorno2);

        Collection<Cliente1> list1 = clienteDAO1.buscarTodos();
        assertTrue(list1 != null);

        Collection<Cliente2> list2 = clienteDAO2.buscarTodos();
        assertTrue(list2 != null);
    }

    private Cliente2 criarCliente2() {
        Cliente2 cliente2 = new Cliente2();
        cliente2.setCpf(rd.nextLong());
        cliente2.setNome("Matteo");
        cliente2.setCidade("Porto Alegre");
        cliente2.setEnd("End");
        cliente2.setEstado("RS");
        cliente2.setNumero(5);
        cliente2.setTel(513333333L);
        return cliente2;
    }

    private Cliente1 criarCliente() {
        Cliente1 cliente1 = new Cliente1();
        cliente1.setCpf(rd.nextLong());
        cliente1.setNome("Rodrigo");
        cliente1.setCidade("São Paulo");
        cliente1.setEnd("End");
        cliente1.setEstado("SP");
        cliente1.setNumero(10);
        cliente1.setTel(1199999999L);
        return cliente1;
    }

}
