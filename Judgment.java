package Project;

import java.util.Set;

public class Judgment extends AbstractDocument {
    public CourtType courtType;
    public String sygnature;
    public Set<Judge> judges;
    public Set<Regulation> regulations;

    public Judgment(long id, CourtType cType, String date, String sygnature, String description) {
        this.id = id;
        this.sygnature = sygnature;
        this.courtType = cType;
        this.publicationDate = date;
        this.description = description;
    }

    public String toString() {
       return "Syg: " + sygnature + " typ: " + courtType.toString() + " data: " + publicationDate;
    }

    public void showInformations() {
        System.out.println(this.toString());
    }

    public String showDescription() {
        return this.description;
    }

    public void showJudges() {
        for (Judge judge : judges) {
            System.out.println(judge);
        }
    }

    public void showRegulations() {
        for (Regulation regulation : regulations) {
            System.out.println(regulation);
        }
    }
}
