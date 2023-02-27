package med.voll.api.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.CreateMedicosDto;
import med.voll.api.medico.UpdateMedicoDto;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.MedicoView;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid CreateMedicosDto medicosDto) {
        repository.save(new Medico(medicosDto));
    }

    @GetMapping
    public Page<MedicoView> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(MedicoView::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UpdateMedicoDto dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atalizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        //Deletar médico do banco de dados
        //repository.deleteById(id);
        
        //Exclusão Lógica 
        var medico = repository.getReferenceById(id);
        medico.deletar();
    }
}