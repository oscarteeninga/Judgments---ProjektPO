package Modules;

public enum CourtType {
    Common, Supreme, Constitutional, Main, Province, Chamber, Unknown;

    public String toString() {
        switch(this) {
            case Common: return "Sąd powszechny";
            case Supreme: return "Sąd najwyższy";
            case Constitutional: return "Trybunał konstytucyjny";
            case Main: return "Naczelny Sąd Administracyjny";
            case Province: return "Wojedódzki Sąd Administracyjny";
            case Chamber: return "Krajowa Izba Odwoławcza";
            default: return "Unknown";
        }
    }
}
