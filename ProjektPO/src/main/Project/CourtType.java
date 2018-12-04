package Project;

public enum CourtType {
    Common, Supreme, Constitutional, Unknown;

    public String toString() {
        switch(this) {
            case Common: return "Sąd powszechny";
            case Supreme: return "Sąd najwyższy";
            case Constitutional: return "Trybunał konstytucyjny";
            default: return "Unknown";
        }
    }
}
