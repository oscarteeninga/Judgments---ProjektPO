package Modules;

public class CourtTypeParser {
    public CourtType parse (String type) {
        switch (type) {
            //Dla JSON
            case "COMMON": return CourtType.Common;
            case "SUPREME": return CourtType.Supreme;
            case "CONSTITUTIONAL_TRIBUNAL": return CourtType.Constitutional;
            case "NATIONAL_APPEAL_CHAMBER": return CourtType.Chamber;
            //Dla HTML
            case "Wojewódzki": return CourtType.Province;
            case "Naczelny": return CourtType.Main;
            default: return CourtType.Unknown;
        }
    }
}
