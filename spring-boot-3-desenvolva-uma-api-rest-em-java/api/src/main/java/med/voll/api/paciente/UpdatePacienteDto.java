package med.voll.api.paciente;

import jakarta.validation.Valid;
import med.voll.api.endereco.CreateEnderecoDto;

public record UpdatePacienteDto(
        Long id,
        String nome,
        String telefone,
        @Valid CreateEnderecoDto endereco) {
}