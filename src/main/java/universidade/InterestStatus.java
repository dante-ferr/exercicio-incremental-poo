package universidade;

public enum InterestStatus {
    INTERESTED, WITHDRAWN, COMPLETED;

    @Override
    public String toString() {
        switch (this) {
            case INTERESTED: return "Interessado";
            case WITHDRAWN: return "Desistiu";
            case COMPLETED: return "Finalizado";
            default: return super.toString();
        }
    }
}
