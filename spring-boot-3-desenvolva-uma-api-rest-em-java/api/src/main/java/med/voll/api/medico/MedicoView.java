package med.voll.api.medico;

public record MedicoView(
        Long id,
        String nome,
        String Email,
        String crm,
        Especialidade especialidade) {

    public MedicoView(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
