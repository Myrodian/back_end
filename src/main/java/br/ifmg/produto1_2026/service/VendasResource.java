package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendasResource {
    private AtivacaoClienteService ativacaoCliente;

    public VendasResource(AtivacaoClienteService ativacaoCliente) {
        this.ativacaoCliente = ativacaoCliente;
        System.out.println("Camada de resource executada");
    }

    @PostMapping
    public ResponseEntity<String> insert() {
        Usuario entity = new Usuario();
        entity.setNome("Fernando");
        entity.setTelefone("+5555555555");
        entity.setEmail("fernando@gmail.com");
        ativacaoCliente.ativar(entity,"ativado...");
        return ResponseEntity.ok().body("Venda Realizada");
    }
}
