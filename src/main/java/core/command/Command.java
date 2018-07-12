package core.command;

import core.AgentEngine;

import java.net.URL;

public class Command
{

    public void getResource() {

    }
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
