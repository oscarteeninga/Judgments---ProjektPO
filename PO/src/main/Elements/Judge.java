package Elements;

import Modules.Function;
import Modules.Role;

import java.util.HashSet;
import java.util.Set;

public class Judge {
    public String name;
    public Function function;
    public Set<Role> roles;
    public Set<Judgment> judgments = new HashSet<>();

    public Judge (String name, Function functions, Set<Role> roles) {
        this.name = name;
        this.function = functions;
        this.roles = roles;
    }

    public String toString() {
        String toReturn = this.name + " (Funkcja:";
        toReturn += (" " + function.toString());
        toReturn += (" Rola:");
        for (Role role: this.roles) { toReturn += (" " + role.toString()); }
        toReturn += ")";
        return toReturn;
    }

    public void printJudgments() {
        for (Judgment judgment : judgments) {
            System.out.println("        | " + judgment);
        }
    }
}
