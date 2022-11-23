package one.digitalinnovation.padroesprojetospring.service;

import one.digitalinnovation.padroesprojetospring.model.Cliente;

public interface ClienteService {
  
  Iterable<Cliente> buscarTodos();

  Cliente buscarPorId(Long id);

  void inserirCliente(Cliente cliente);

  void atualizarCliente(Long id, Cliente cliente);

  void deletarCliente(Long id);

}
