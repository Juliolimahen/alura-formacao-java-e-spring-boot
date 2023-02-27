package med.voll.api.paciente;

public record PacienteView (Long id, String nome, String email, String cpf) {
    public PacienteView(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}