package Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class JSONFile {
    JSONParser parser = new JSONParser();
    JSONArray judgmentsList = new JSONArray();

    public JSONFile (String[] path) {
        try {
            for (String onePath : path) {
                Object obj = parser.parse(new FileReader(onePath));
                JSONObject rulesObj = (JSONObject) obj;
                JSONArray rules = (JSONArray) rulesObj.get("items");
                for (int i = 0; i < rules.size(); i++) {
                    judgmentsList.add(rules.get(i));
                }
            }
        }
        catch (ParseException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }

    public Judgment loadJudgment(JSONObject oneJudgment) {
        long id = (long) oneJudgment.get("id");
        CourtType courtType = (new CourtTypeParser()).parse((String) oneJudgment.get("courtType"));
        String date = (String) oneJudgment.get("judgmentDate");
        String description = (String) oneJudgment.get("textContent");
        JSONArray sygArray = (JSONArray) oneJudgment.get("courtCases");
        JSONObject sygObject = (JSONObject) sygArray.get(0);
        String sygnature = (String) sygObject.get("caseNumber");
        Judgment result = new Judgment(id, courtType, date, sygnature, description);
        Set<Judge> judges = this.loadJudges(oneJudgment);
        Set<Regulation> regulations = this.loadRegulations(oneJudgment);
        result.judges = judges;
        result.regulations = regulations;
        return result;
    }

    public Set<Judge> loadJudges(JSONObject oneJudgment) {
        JSONArray judges = (JSONArray) oneJudgment.get("judges");
        Set <Judge> result = new HashSet<Judge>();
        for (int j = 0; j < judges.size(); j++) {
            JSONObject oneJudge = (JSONObject) judges.get(j);
            JSONArray oneJudgeRoles = (JSONArray) oneJudge.get("specialRoles");
            Set<Role> roles = new HashSet<>();
            for (int i = 0; i < oneJudgeRoles.size(); i++) {
                String oneRole = (String) oneJudgeRoles.get(i);
                roles.add(new RoleParser().parse(oneRole));
            }
            Function function;
            if (oneJudge.get("function") == null) function = Function.No;
            else function = new FunctionParser().parse((String) oneJudge.get("function"));
            result.add(new Judge((String) oneJudge.get("name"), function, roles));
        }
        return result;
    }

    public Set<Regulation> loadRegulations(JSONObject oneJudgment) {
        JSONArray regulations = (JSONArray) oneJudgment.get("referencedRegulations");
        Set<Regulation> result = new HashSet<Regulation>();
        for (int i = 0; i < regulations.size(); i++) {
            JSONObject oneRegulation = (JSONObject) regulations.get(i);
            long id = (long) oneRegulation.get("journalNo");
            String regName = (String) oneRegulation.get("journalTitle");
            String date = String.valueOf(oneRegulation.get("journalYear"));
            String description = (String) oneRegulation.get("text");
            result.add(new Regulation(id, regName, date, description));
        }
        return result;
    }
}
