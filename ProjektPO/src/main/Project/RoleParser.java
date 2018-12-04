package Project;

public class RoleParser {
    public Role parse (String type) {
        switch (type) {
            case "PRESIDING_JUDGE": return Role.Presinding;
            case "REASONS_FOR_JUDGMENT_AUTHOR": return Role.Reasons;
            case "REPORTING_JUDGE": return Role.Reporting;
            default: return Role.Unknown;
        }
    }
}
