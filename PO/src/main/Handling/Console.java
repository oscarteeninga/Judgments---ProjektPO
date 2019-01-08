package Handling;

import java.io.IOException;

import jline.console.ConsoleReader;

public class Console {
    public static void main(String[] args) {
        try {
            ConsoleHandling cHandling = new ConsoleHandling();
            ConsoleReader console = new ConsoleReader();
            console.setPrompt(">");
            cHandling.printConsole(console);
            String line;
            while (!(line = console.readLine()).equals("exit")) {
                if (cHandling.lineHasPause(line) == 0) {
                    switch (line) {
                        case "help":
                            cHandling.printHelp();
                            break;
                        case "months":
                            cHandling.printMonths();
                            break;
                        case "courts":
                            cHandling.printCourts();
                            break;
                        case "judges":
                            cHandling.printTopJudges();
                            break;
                        case "regulations":
                            cHandling.printTopTenRegulations();
                            break;
                        case "jury":
                            cHandling.printJudgePerJudgmentStatistic();
                            break;
                        default:
                            System.out.println("Nie znaleziono komendy, wpisz help, aby uzyskać spis komend.");
                    }
                }
                else {
                    String command = "";
                    for (int i = 0; i < cHandling.lineHasPause(line); i++) command += line.charAt(i);
                    switch (command) {
                        case "rubrum":
                            cHandling.printRubrum(line);
                            break;
                        case "judge":
                            cHandling.printJudge(line);
                            break;
                        case "content":
                            cHandling.printContent(line);
                            break;
                        default:
                            System.out.println("Nie znaleziono komendy <niedozwolone są niepotrzebne spacje!>");
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
