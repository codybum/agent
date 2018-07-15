package core;


import core.command.Command;
import org.osgi.framework.Bundle;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;

public class AgentEngine
{

    public static void main(String[] argv) throws Exception {
        // Print welcome banner.
        //System.out.println("\nWelcome to My Launcher");
        //System.out.println("======================\n");

        System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");


        String configFile = null;
        if(argv.length > 1) {
            configFile =  argv[1];
        } else {
           configFile = "agent.ini";
        }

        File agentConfig = new File(configFile);
        if(agentConfig.isFile()) {
            System.setProperty("agentConfig",agentConfig.getAbsolutePath());
        }

       /*
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        java.util.logging.Logger ODBLogger = java.util.logging.Logger.getLogger("com.orientechnologies");
        ODBLogger.setLevel(Level.SEVERE);

        java.util.logging.Logger AMQLogger = java.util.logging.Logger.getLogger("org.apache.activemq");
        AMQLogger.setLevel(Level.SEVERE);

        java.util.logging.Logger apacheCommonsLogger = java.util.logging.Logger.getLogger("org.apache.commons.configuration");
        apacheCommonsLogger.setLevel(Level.SEVERE);
        */

        HostApplication ha = new HostApplication();

    }
}