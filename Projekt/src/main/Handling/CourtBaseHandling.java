package Handling;

import Elements.Judge;
import Elements.Regulation;
import Modules.CourtType;

import java.util.HashSet;
import java.util.Set;

public class CourtBaseHandling {
    public void printRubrum(String syg, CourtBase courtBase) {
        for (String key : courtBase.judgments.keySet()) {
            if (courtBase.judgments.get(key).sygnature.equals(syg)) {
                courtBase.judgments.get(key).showInformations();
                System.out.println("Skład sędziów:");
                courtBase.judgments.get(key).showJudges();
                System.out.println("");
            }
        }
    }

    public void printJudge(String sname, CourtBase courtBase) {
        for (Judge judge : courtBase.judges) {
            if (judge.name.equals(sname)) {
                 judge.printJudgments();
                 break;
            }
        }
    }

    public void printTopTenJudges(CourtBase courtBase) {
        Set<Judge> judgesCpy = new HashSet<>();
        judgesCpy.addAll(courtBase.judges);
        for (int i = 0; i < 10 && !judgesCpy.isEmpty(); i++) {
            int max = -1;
            Judge maxJudge = new Judge("", null, null);
            for (Judge judge : judgesCpy) {
                if (max < judge.judgments.size()) {
                    max = judge.judgments.size();
                    maxJudge = judge;
                }
            }
            System.out.print(i+1 + ".     " + maxJudge + ": ");
            System.out.println(maxJudge.judgments.size());
            maxJudge.printJudgments();
            System.out.println("");
            judgesCpy.remove(maxJudge);
        }
    }

    public void printTopTenRegulations(CourtBase courtBase) {
        Set<Regulation> regulationsCpy = new HashSet<>();
        regulationsCpy.addAll(courtBase.regulations);
        for (int i = 0; i < 11 && !regulationsCpy.isEmpty(); i++) {
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

    public void printSortByDate(CourtBase courtBase) {
        int minyear = 9;
        for (String key : courtBase.judgments.keySet()) {
            int currYear = (int) courtBase.judgments.get(key).publicationDate.charAt(3) - '0';
            if (currYear < minyear) minyear = currYear;
        }
        for (; minyear < 9; minyear++) {
            for (int monthOne = 0; monthOne <= 1; monthOne++) {
                for (int monthTwo = 0; monthTwo <= 9 && 10*monthOne + monthTwo <= 12; monthTwo++) {
                    int count = 0;
                    for (String key : courtBase.judgments.keySet()) {
                        int currYear = (int) courtBase.judgments.get(key).publicationDate.charAt(3) - '0';
                        int currMonthOne = (int) courtBase.judgments.get(key).publicationDate.charAt(5) - '0';
                        int currMonthTwo = (int) courtBase.judgments.get(key).publicationDate.charAt(6) - '0';
                        if (currYear == minyear && currMonthOne == monthOne && currMonthTwo == monthTwo) count++;
                    }
                    if (count != 0) System.out.println("201" + minyear + "-" + monthOne + monthTwo + ": " + count + " orzeczeń");
                }
            }
        }
    }

    public void printStatisticCourtType (CourtBase courtBase) {
        int C = 0, TK = 0, SN = 0, UN = 0, WS = 0, NS = 0, KI = 0;
        for (String key : courtBase.judgments.keySet()) {
            switch (courtBase.judgments.get(key).courtType) {
                case Common: C++; break;
                case Supreme: SN++; break;
                case Constitutional: TK++; break;
                case Main: NS++; break;
                case Province: WS++; break;
                case Chamber: KI++; break;
                default: UN++;
            }
        }
        System.out.println (CourtType.Common.toString() + ": " + C);
        System.out.println (CourtType.Supreme.toString() + ": " + SN);
        System.out.println (CourtType.Constitutional.toString() + ": " + TK);
        System.out.println (CourtType.Province.toString() + ": " + WS);
        System.out.println (CourtType.Chamber.toString() + ": " + KI);
        System.out.println (CourtType.Main.toString() + ": " + NS);
        System.out.println ("Nieokreślone: " + UN);

    }

    public void printJudgesPerJudgmentsStatistic(CourtBase courtBase) {
        int sum = 0;
        int count = 0;
        for (String key :courtBase.judgments.keySet()) {
            System.out.println("Syg: " + courtBase.judgments.get(key).sygnature + ": " + courtBase.judgments.get(key).judges.size());
            count++;
            sum+= courtBase.judgments.get(key).judges.size();
        }
        System.out.println("Średnio: " + (float) sum/count);
    }

    public void printContent(String syg, CourtBase courtBase) {
        for (String key : courtBase.judgments.keySet()) {
            if (courtBase.judgments.get(key).sygnature.equals(syg)) {
                System.out.println(courtBase.judgments.get(key).description);
            }
        }
    }
}
