package Handling;

import Loaders.*;

import java.io.File;

public class GenerateCourtBase {
    public CourtBase generate(File dirHTML, File dirJSON) {
        JSONFile jsonFile = new JSONFile(dirJSON);
        CourtBase courtBaseJSON = jsonFile.load();
        HTMLFile htmlFile = new HTMLFile(dirHTML);
        CourtBase courtBaseHTML = htmlFile.load();
        courtBaseJSON.addBase(courtBaseHTML);
        return courtBaseJSON;
    }
}
