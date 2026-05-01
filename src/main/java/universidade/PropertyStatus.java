package universidade;

public enum PropertyStatus {
    AVAILABLE, RENTED, MAINTENANCE, UNAVAILABLE;

    @Override
    public String toString() {
        switch (this) {
            case AVAILABLE: return "Disponível";
            case RENTED: return "Alugado";
            case MAINTENANCE: return "Manutenção";
            case UNAVAILABLE: return "Indisponível";
            default: return super.toString();
        }
    }
}
