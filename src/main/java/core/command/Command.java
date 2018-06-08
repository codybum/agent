package core.command;

public class Command
{
    public String getName() {
        System.out.println("woot");
        return "name";
    }
    public String getDescription() {
        return "desc";
    }
    public boolean execute(String commandline) {
     return true;
    }
}
