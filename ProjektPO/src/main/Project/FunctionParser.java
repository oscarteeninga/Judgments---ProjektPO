package Project;

public class FunctionParser {
    public Function parse (String type) {
        switch (type) {
            case "SSN": return Function.SSN;
            default: return Function.No;
        }
    }
}
