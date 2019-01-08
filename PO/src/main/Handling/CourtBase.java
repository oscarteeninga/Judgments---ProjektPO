package Handling;

import Elements.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourtBase {
    public HashMap<String, Judgment> judgments = new HashMap<>();
    public Set<Judge> judges = new HashSet<Judge>();
    public Set<Regulation> regulations = new HashSet<Regulation>();

    public void addBase(CourtBase courtBase) {
        for (Map.Entry<String, Judgment> judgment: courtBase.judgments.entrySet()) {
            this.addJudgment(judgment.getValue(), judgment.getKey());
        }
    }

    private void addJudge(Judge newJudge, Judgment judgment) {
        boolean isIn = false;
        for (Judge judge : this.judges) {
            if (judge.name.equals(newJudge.name)) {
                isIn = true;
                judge.roles.addAll(judge.roles);
                judge.judgments.add(judgment);
                break;
            }
        }
        if (!isIn) {
            this.judges.add(newJudge);
            this.addJudge(newJudge, judgment);
        }
    }

    public void addJudgment(Judgment newJudgment, String key) {
        boolean isIn = false;
        for (Judgment judgment : this.judgments.values()) {
            if (judgment.sygnature.equals(newJudgment.sygnature)) isIn = true;
        }
        if (!isIn) {
            this.judgments.put(key, newJudgment);
            for (Judge judge : newJudgment.judges) this.addJudge(judge, newJudgment);
            for (Regulation regulation : newJudgment.regulations) this.addRegulation(regulation, newJudgment);
        }
    }

    private void addRegulation(Regulation newRegulation, Judgment newJudgment) {
        boolean isIn = false;
        for (Regulation regulation : this.regulations) {
            if (regulation.name.equals(newRegulation.name)) {
                isIn = true;
                regulation.judgments.add(newJudgment);
            }
        }
        if (!isIn) {
            this.regulations.add(newRegulation);
            this.addRegulation(newRegulation, newJudgment);
        }
    }
}
