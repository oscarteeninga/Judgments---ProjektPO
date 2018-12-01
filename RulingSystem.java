package Project;

import java.io.IOException;

public class RulingSystem {
    public static void main(String []args) {
        String[] filePaths = {"judgments/judgments-348.json", "judgments/judgments-520.json", "judgments/judgments-924.json", "judgments/judgments-995.json", "judgments/judgments-1117.json", "judgments/judgments-1287.json", "judgments/judgments-1324.json", "judgments/judgments-1338.json", "judgments/judgments-1912.json"};
        CourtBase list = new CourtBase(filePaths);
        long id[] = {283463, 195537, 53061};
        String syg[] = {"I ACa 232/15", "II Cz 256/14", "II AKa 105/11"};
    }
}
