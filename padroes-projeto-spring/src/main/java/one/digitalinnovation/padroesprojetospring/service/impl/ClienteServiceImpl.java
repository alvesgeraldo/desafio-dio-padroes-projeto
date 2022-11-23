package one.digitalinnovation.padroesprojetospring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.padroesprojetospring.model.Cliente;
import one.digitalinnovation.padroesprojetospring.model.ClienteRepository;
import one.digitalinnovation.padroesprojetospring.model.Endereco;
import one.digitalinnovation.padroesprojetospring.model.EnderecoRepository;
import one.digitalinnovation.padroesprojetospring.service.ClienteService;
import one.digitalinnovation.padroesprojetospring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ViaCepService viaCepService;

  @Override
  public void atualizarCliente(Long id, Cliente cliente) {
		// Buscar Cliente por ID, caso exista:
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteBd(cliente);
		}
	}

  @Override
  public Cliente buscarPorId(Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.get();
  }

  @Override
  public Iterable<Cliente> buscarTodos() {
    return clienteRepository.findAll();
  }

  @Override
  public void deletarCliente(Long id) {
    clienteRepository.deleteById(id);    
  }

  @Override
  public void inserirCliente(Cliente cliente) {

    this.salvarClienteBd(cliente);
    
  }

  private void salvarClienteBd(Cliente cliente) {
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      Endereco novoEndereco = viaCepService.consutarCEP(cep);
      enderecoRepository.save(novoEndereco);
      return novoEndereco;
    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);
  }
  
}
