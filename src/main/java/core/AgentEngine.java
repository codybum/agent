package core;


import core.command.Command;
import core.command.DiscoveryCrypto;
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

        String verifyMessage = "DISCOVERY_MESSAGE_VERIFIED";
        String discoveryValidator = "tester";
        /*
        String verifyMessage = "DISCOVERY_MESSAGE_VERIFIED";
                String discoveryValidator = rme.getParam("discovery_validator");
                String decryptedString = discoveryCrypto.decrypt(discoveryValidator,discoverySecret);

         */

        /*
        DiscoveryCrypto dc = new DiscoveryCrypto();

        String enc = dc.encrypt(verifyMessage,discoveryValidator);
        System.out.println("enc: " + enc);

        System.out.println(dc.decrypt(enc,discoveryValidator));

        String test = "+MhIeU5DQx4m9U68XLlylKuRGJItWVuBkuxIvaXaguk=";

        System.out.println(dc.decrypt(test,discoveryValidator));


        System.exit(0);
*/

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