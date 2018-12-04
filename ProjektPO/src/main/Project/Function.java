package Project;

public enum Function {
    SSN, No;
    public String toString() {
        switch(this) {
            case SSN: return "Sędzia Sądu Najwyższego";
            default: return "";
        }
    }
}
