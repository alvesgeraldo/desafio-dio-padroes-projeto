package one.digitalinnovation.padroesprojetospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.padroesprojetospring.model.Cliente;
import one.digitalinnovation.padroesprojetospring.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public ResponseEntity<Iterable<Cliente>> buscarTodos(){
    return ResponseEntity.ok(clienteService.buscarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
    return ResponseEntity.ok(clienteService.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente){
    clienteService.inserirCliente(cliente);
    return ResponseEntity.ok(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, Cliente cliente){
    clienteService.atualizarCliente(id, cliente);
    return ResponseEntity.ok(cliente);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id){
    clienteService.deletarCliente(id);
    return ResponseEntity.ok().build();
  }
}
