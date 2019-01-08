package Modules;

public class RoleParser {
    public Role parse (String type) {
        switch (type) {
            //Dla JSON
            case "PRESIDING_JUDGE": return Role.Presinding;
            case "REASONS_FOR_JUDGMENT_AUTHOR": return Role.Reasons;
            case "REPORTING_JUDGE": return Role.Reporting;
            //Dla HTML
            case "przewodniczący": return Role.Presinding;
            case "sprawozdawca": return Role.Reasons;
            default: return Role.Unknown;
        }
    }
}
