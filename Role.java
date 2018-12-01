package Project;

public enum Role {
    Presinding, Reasons, Reporting, Unknown;

    public String toString() {
        switch(this) {
            case Presinding: return "Przewodniczący";
            case Reasons: return "Prokurator";
            case Reporting: return "Oskarżyciel";
            default: return "";
        }
    }
}
