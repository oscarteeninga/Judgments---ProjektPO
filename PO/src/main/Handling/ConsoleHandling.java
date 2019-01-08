package Handling;

import jline.console.ConsoleReader;

import java.io.File;
import java.io.IOException;

public class ConsoleHandling {
    private CourtBase courtBase;
    private CourtBaseHandling CBH;

    public  ConsoleHandling () {
        CBH = new CourtBaseHandling();
    }

    protected void printConsole (ConsoleReader console) {
        try {
            System.out.println("==================================================================================================");
            System.out.println("                                          BAZA ORZECZEŃ                                           ");
            System.out.println("==================================================================================================");
            System.out.println("Wprowadź ścieżkę dostępu do plików HTML/JSON: ");
            String path = console.readLine();
            File dir = new File(path);
            courtBase = new GenerateCourtBase().generate(dir, dir);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    protected void printHead(String head) {
        int i = 0;
        for (; i < (98-head.length())/2; i++) System.out.print("-");
        System.out.print(head);
        for (i += head.length(); i < 98; i++) System.out.print("-");
        System.out.println("");
    }

    protected void printBack() {
        System.out.println("--------------------------------------------------------------------------------------------------");
System.out.println("");
    }

    protected int lineHasPause (String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') return i;
        }
        return 0;
    }

    protected void printHelp () {
        printHead("Spis komend");
        System.out.println("§1.  rubrum <syg>, <syg>  - wyświetl orzeczenia o danych sygnaturach.");
        System.out.println("§2.  content <syg>        - wyświetla uzasdnienie.");
        System.out.println("§3.  judge <imie> <nazw>  - wyświetl orzeczenia dla danego sędziego.");
        System.out.println("§4.  judges               - wyświetla 10 sędziów o największej liczbie orzeczeń.");
        System.out.println("§5.  months               - wyświetlał liczbę orzeczeń w poszczególnych miesiącach.");
        System.out.println("§6.  courts               - wyświetlał liczbę orzeczeń ze względu na typ sądu.");
        System.out.println("§7.  regulations          - wyświetlał 10 najczęściej przywoływanych ustaw.");
        System.out.println("§8.  jury                 - wyświetlał liczbę spraw przypadających na określony skład sędziowski.");
        printBack();
    }

    protected void printRubrum(String syg) {
        int i = this.lineHasPause(syg) + 1;
        while (i < syg.length()) {
            String actualSyg = "";
            while(i < syg.length() && syg.charAt(i) != ',') {
                actualSyg += syg.charAt(i);
                i++;
            }
            printHead("Sprawa " + actualSyg);
            CBH.printRubrum(actualSyg, courtBase);
            i+=2;
        }
        printBack();
    }

    protected void printContent(String line) {
        int i = this.lineHasPause(line) + 1;
        String syg = "";
        while (i < line.length()) {
            syg += line.charAt(i);
            i++;
        }
        printHead("Uzasadnienie dla " + syg);
        CBH.printContent(syg, courtBase);
        printBack();
    }

    protected void printJudge(String line) {
        int i = this.lineHasPause(line) + 1;
        String name = "";
        for (;i < line.length(); i++) name += line.charAt(i);
        printHead("Sędzia " + name);
        System.out.println("");
        CBH.printJudge(name, courtBase);
        printBack();

    }

    protected void printTopJudges() {
        printHead("Najaktywniejsi sędziowie");
        CBH.printTopTenJudges(courtBase);
        printBack();
    }

    protected void printMonths() {
        printHead("Rozkład ilości rozpraw ze względu na miesiące");
        CBH.printSortByDate(courtBase);
        printBack();
    }

    protected void printCourts() {
        this.printHead("Rozkład statystyczny ze względu na rodzaj sądu");
        CBH.printStatisticCourtType(courtBase);
        printBack();
    }

    protected void printJudgePerJudgmentStatistic() {
        this.printHead("Rozkład statystyczny ilości sędziów ze względu na orzeczenie");
        CBH.printJudgesPerJudgmentsStatistic(courtBase);
        printBack();
    }

    protected void printTopTenRegulations() {
        this.printHead("Najczęściej przywoływane ustawy");
        CBH.printTopTenRegulations(courtBase);
        printBack();
    }
}
