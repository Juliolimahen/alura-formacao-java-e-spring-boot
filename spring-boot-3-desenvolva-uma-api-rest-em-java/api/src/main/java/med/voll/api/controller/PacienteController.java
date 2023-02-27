package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.CreatePacienteDto;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.PacienteView;
import med.voll.api.paciente.UpdatePacienteDto;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CreatePacienteDto pacienteDto) {
        repository.save(new Paciente(pacienteDto));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UpdatePacienteDto dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

    @GetMapping
    public Page<PacienteView> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteView::new);
    }
}
