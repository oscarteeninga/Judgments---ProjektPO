package Loaders;

import Handling.CourtBase;

import Elements.*;
import Modules.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HTMLFile {
    private ArrayList<Document> judgmentsList;

    public HTMLFile(File dir) {
        try {
            judgmentsList = new ArrayList<>();
            for (File fileHTML : dir.listFiles()) {
                File input = new File(fileHTML.getAbsolutePath());
                if(input.toString().charAt(input.toString().length()-1) == 'l'){
                    Document judgeDocument = Jsoup.parse(input, "UTF-8", "http://example.com/");
                    judgmentsList.add(judgeDocument);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public CourtBase load() {
        CourtBase result = new CourtBase();
        for (int i = 0; i < judgmentsList.size(); i++) {
            Judgment newJudgment = this.loadJudgment(judgmentsList.get(i));
            result.addJudgment(newJudgment, newJudgment.sygnature);
        }
        return result;
    }

    public Judgment loadJudgment(Document oneJudgment) {
        //Sygnature
        Element sygElement = oneJudgment.getElementsByTag("span").get(2);
        String sygString = sygElement.text();
        String sygnature = "";
        for (int i = 1; sygString.charAt(i) != '-'; i++) {
            sygnature += sygString.charAt(i - 1);
        }
        //Date
        String date = oneJudgment.getElementsByClass("info-list-value").get(1).text();
        //CourtType
        String courtTypeStr = oneJudgment.getElementsByClass("info-list-value").get(2).text();
        String courtTypeToParse = "";
        for (int i = 0; courtTypeStr.charAt(i) != ' '; i++) {
            courtTypeToParse += courtTypeStr.charAt(i);
        }
        CourtType courtType = new CourtTypeParser().parse(courtTypeToParse);
        //Description
        String description = oneJudgment.getElementsByClass("info-list-value-uzasadnienie").get(0).text();
        //Judges
        Set<Judge> judges = new HashSet<>();
        String judgesStr = oneJudgment.getElementsByClass("info-list-value").get(3).toString();
        String oneJudge = "";
        for (int i = 29; i + 4 < judgesStr.length(); i++) {
            if (judgesStr.charAt(i) == '<') {
                Judge newJudge = this.loadJudge(oneJudge);
                judges.add(newJudge);
                oneJudge = "";
                i += 4;
            }
            oneJudge += judgesStr.charAt(i);
        }
        //Regulations
        String regulationsStr = oneJudgment.getElementsByClass("nakt").text();
        if (regulationsStr.length() > 1) regulationsStr = oneJudgment.getElementsByClass("nakt").last().text();
        Regulation newRegulation = this.loadRegulation(regulationsStr);
        Set<Regulation> regulations = new HashSet<>();
        if (newRegulation != null) regulations.add(newRegulation);
        //Full judgment
        Judgment result = new Judgment((long) 0, courtType, date, sygnature, description);
        result.judges = judges;
        result.regulations = regulations;
        return result;
    }

    private Judge loadJudge(String oneJudge) {
        String name = "";
        int i;
        for (i = 0; i < oneJudge.length() && oneJudge.charAt(i) != '/'; i++) {
            name += oneJudge.charAt(i);
        }
        String newName = "";
        for (int j = 0; j < name.length() - 1 && name.length() != oneJudge.length(); j++) {
            newName += name.charAt(j);
        }
        if (name.length() == oneJudge.length()) newName = name;
        Set<Role> roles = new HashSet<>();
        while (i < oneJudge.length()) {
            String role = "";
            for (i++; i < oneJudge.length() && oneJudge.charAt(i) != '/'; i++) {
                role += oneJudge.charAt(i);
            }
            roles.add(new RoleParser().parse(role));
            i++;
        }
        return new Judge(newName, Function.No, roles);
    }

    private Regulation loadRegulation(String oneReg) {
        if (oneReg.length() > 1) {
            String regName = "";
            boolean done = false;
            for (int i = 30; i < oneReg.length(); i++) {
                while ((oneReg.charAt(i) == ' '
                        || oneReg.charAt(i) == 'r'
                        || oneReg.charAt(i) == '.'
                        || oneReg.charAt(i) == '-') && !done) i++;
                done = true;
                regName += oneReg.charAt(i);
            }
            return new Regulation(0, regName, "", "");
        }
        return null;
    }
}
