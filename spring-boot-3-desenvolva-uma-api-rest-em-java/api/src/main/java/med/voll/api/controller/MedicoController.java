package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void Cadastrar( @RequestBody @Valid CreateMedicosDto medicosDto){
        repository.save(new Medico(medicosDto));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UpdateMedicoDto dados) {
        var medico = repository.getReferenceById(dados.id());
    }

}