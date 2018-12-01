package Project;

public class CourtTypeParser {
    public CourtType parse (String type) {
        switch (type) {
            case "COMMON": return CourtType.Common;
            case "SUPREME": return CourtType.Supreme;
            case "CONSTITUTIONAL_TRIBUNAL": return CourtType.Constitutional;
            default: return CourtType.Unknown;
        }
    }
}
