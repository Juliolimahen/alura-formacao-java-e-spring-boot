package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.CreateEnderecoDto;

public record UpdateMedicoDto (
@NotNull
Long id,
String nome,
String telefone,
CreateEnderecoDto endereco){
}
