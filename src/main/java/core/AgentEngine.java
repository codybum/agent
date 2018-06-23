package core;


import org.osgi.framework.Bundle;

public class AgentEngine
{

    public static void main(String[] argv) throws Exception {
        // Print welcome banner.
        System.out.println("\nWelcome to My Launcher");
        System.out.println("======================\n");

        HostApplication ha = new HostApplication();
        ha.execute("test","test");

        /*
        for(Bundle b : ha.getInstalledBundles()) {
            System.out.println(b.getBundleId());

        }
        */

        /*
        while(true) {
            ha.printb();
            Thread.sleep(5000);
        }
        */
    }
}