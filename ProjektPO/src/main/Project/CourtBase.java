package Project;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CourtBase {
    HashMap<String, Judgment> judgments = new HashMap<>();
    Set<Judge> judges = new HashSet<Judge>();
    Set<Regulation> regulations = new HashSet<Regulation>();

    public CourtBase (String []filePath) {
        JSONFile judgmentsList = new JSONFile(filePath);
        for (int i = 0; i < judgmentsList.judgmentsList.size(); i++) {
            JSONObject oneJudgment = (JSONObject) judgmentsList.judgmentsList.get(i);
            Judgment newJudgment = judgmentsList.loadJudgment(oneJudgment);
            this.judgments.put(newJudgment.sygnature, newJudgment);
            for (Judge judge : newJudgment.judges) {
                boolean isIn = false;
                for (Judge judge1 : this.judges) {
                    if (judge.name.equals(judge1.name)) {
                        judge1.roles.addAll(judge.roles);
                        isIn = true;
                    }
                }
                if (!isIn) this.judges.add(judge);
            }
            for (Regulation regulation : newJudgment.regulations) {
                boolean isIn = false;
                for (Regulation regulation1 : this.regulations) {
                    if (regulation.name.equals(regulation1.name)) isIn = true;
                }
                if (!isIn) this.regulations.add(regulation);
            }
        }
        for (Judge judge : this.judges) {
            for (String key : judgments.keySet()) {
                for (Judge judge1 : judgments.get(key).judges) {
                    if (judge.name.equals(judge1.name)) {
                        judge.judgments.add(judgments.get(key));
                    }
                }
            }
        }
        for (Regulation regulation : regulations) {
            for (String key : judgments.keySet()) {
                for (Regulation regulation1 : judgments.get(key).regulations) {
                    if (regulation1.name.equals(regulation.name)) {
                        regulation.judgments.add(judgments.get(key));
                    }
                }
            }
        }
        //ładowana jest osobna lista sędziów dla orzeczenia i lista sędziów ogółem, ponieważ różnią się rolami, więc w bazie sędziów role się dodają
        //natomiast dla poszczególnych orzeczeń rola jest dla danego orzeczenia
    }

    public void printRubrum(String syg[]) {
        for (String sygnature : syg) {
            for (String key : this.judgments.keySet()) {
                if (this.judgments.get(key).sygnature.equals(sygnature)) {
                    this.judgments.get(key).showInformations();
                    System.out.println("Skład sędziów:");
                    this.judgments.get(key).showJudges();
                    System.out.println("");
                }
            }
        }
    }
    public void printJudges() {
        for (Judge judge : this.judges) {
            System.out.println(judge);
        }
    }

    public void printTopTenJudges() {
        Set<Judge> judgesCpy = new HashSet<>();
        judgesCpy.addAll(this.judges);
        for (int i = 0; i < 10 && !judgesCpy.isEmpty(); i++) {
            int max = -1;
            Judge maxJudge = new Judge("", null, null);
            for (Judge judge : judgesCpy) {
                if (max < judge.judgments.size()) {
                    max = judge.judgments.size();
                    maxJudge = judge;
                }
            }
            System.out.print(maxJudge + " ");
            System.out.println(maxJudge.judgments.size());
            maxJudge.printJudgments();
            System.out.println("");
            judgesCpy.remove(maxJudge);
        }
    }

    public void printTopTenRegulations() {
        Set<Regulation> regulationsCpy = new HashSet<>();
        regulationsCpy.addAll(this.regulations);
        for (int i = 0; i < 10 && !regulationsCpy.isEmpty(); i++) {
            int max = -1;
            Regulation maxRegulation = new Regulation(0, "", "", "");
            for (Regulation regulation : regulationsCpy) {
                if (max < regulation.judgments.size()) {
                    max = regulation.judgments.size();
                    maxRegulation = regulation;
                }
            }
            System.out.println(maxRegulation + " ");
            System.out.println(maxRegulation.judgments.size());
            System.out.println("");
            regulationsCpy.remove(maxRegulation);
        }
    }

    public void printSortByDate() {
        int minyear = 9;
        for (String key : this.judgments.keySet()) {
            int currYear = (int) this.judgments.get(key).publicationDate.charAt(3) - '0';
            if (currYear < minyear) minyear = currYear;
        }
        for (; minyear < 9; minyear++) {
            for (int monthOne = 0; monthOne <= 1; monthOne++) {
                for (int monthTwo = 0; monthTwo <= 9 && 10*monthOne + monthTwo <= 12; monthTwo++) {
                    int count = 0;
                    for (String key : this.judgments.keySet()) {
                        int currYear = (int) this.judgments.get(key).publicationDate.charAt(3) - '0';
                        int currMonthOne = (int) this.judgments.get(key).publicationDate.charAt(5) - '0';
                        int currMonthTwo = (int) this.judgments.get(key).publicationDate.charAt(6) - '0';
                        if (currYear == minyear && currMonthOne == monthOne && currMonthTwo == monthTwo) count++;
                    }
                    if (count != 0) System.out.println("201" + minyear + "-" + monthOne + monthTwo + ": " + count + " orzeczeń");
                }
            }
        }
    }

    public void printRegulations() {
        for (Regulation regulation : this.regulations) {
            System.out.println(regulation.toString());
        }
    }
    public void printStatisticCourtType () {
        int C = 0, TK = 0, SN = 0, UN = 0;
        for (String key : judgments.keySet()) {
            switch (judgments.get(key).courtType) {
                case Common: C++; break;
                case Supreme: SN++; break;
                case Constitutional: TK++; break;
                default: UN++;
            }
        }
        System.out.println ("Rozkład statystyczny ze względu na rodzaj sądu:");
        System.out.println ("Sąd powszechny: " + C);
        System.out.println ("Sąd najwyższy: " + SN);
        System.out.println ("Trybunał konstytucyjny: " + TK);
        System.out.println ("Nieokreślone: " + UN);
    }

    public void printjudgesPerJudgmentsStatistic() {
        int sum = 0;
        int count = 0;
        for (String key : this.judgments.keySet()) {
            System.out.println("Syg: " + this.judgments.get(key).sygnature + ": " + this.judgments.get(key).judges.size());
            count++;
            sum+= this.judgments.get(key).judges.size();
        }
        System.out.println("Średnio: " + (float) sum/count);
    }
}
