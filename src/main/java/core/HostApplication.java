package core;

import core.command.Command;
import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTracker;

import java.util.*;

public class HostApplication
{
    private HostActivator m_activator = null;
    private Felix m_felix = null;
    private ServiceTracker m_tracker = null;

    public HostApplication()
    {
        System.setProperty("felix.fileinstall.dir", "/Users/cody/IdeaProjects/firstosgi/target/");
        System.setProperty("felix.fileinstall.noInitialDelay", "true");
        System.setProperty("felix.fileinstall.poll", "1000");

        System.out.println("Building OSGi Framework");

        // Create a configuration property map.
        Map configMap = new HashMap();
        // Export the host provided service interface package.
        configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                "core.command; version=1.0.0");

        // make sure the cache is cleaned
        configMap.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);

        // more properties available at: http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html
        configMap.put("ds.showtrace", "true");
        configMap.put("ds.showerrors", "true");


        // Create host activator;
        m_activator = new HostActivator();

        List list = new ArrayList();
        list.add(m_activator);
        configMap.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, list);

        try
        {
            // Now create an instance of the framework with
            // our configuration properties.
            m_felix = new Felix(configMap);
            // Now start Felix instance.
            m_felix.start();
            //m_felix.getBundleContext().installBundle("/Users/cody/IdeaProjects/firstosgi/firstosgi-1.0-SNAPSHOT.jar");
            BundleContext bc = m_felix.getBundleContext();

            bc.installBundle("file:/Users/cody/IdeaProjects/firstosgi/target/firstosgi-1.0-SNAPSHOT.jar");


            //installedBundles.add(context.installBundle("file:./Sandbox/osgiTest/module-a/target/module-a-1.0-SNAPSHOT.jar"));

            for (Bundle bundle : m_activator.getBundles()) {
                if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
                    bundle.start();
                }
            }

        }
        catch (Exception ex)
        {
            System.err.println("Could not create framework: " + ex);
            ex.printStackTrace();
        }


        m_tracker = new ServiceTracker(
                m_activator.getContext(), Command.class.getName(), null);
        m_tracker.open();


    }

    public Bundle[] getInstalledBundles()
    {
        // Use the system bundle activator to gain external
        // access to the set of installed bundles.
        return m_activator.getBundles();
    }

    public boolean execute(String name, String commandline)
    {


        // See if any of the currently tracked command services
        // match the specified command name, if so then execute it.
        Object[] services = m_tracker.getServices();

        for (int i = 0; (services != null) && (i < services.length); i++)
        {
            System.out.println(services[i].getClass().toString());
            try
            {
                if (((Command) services[i]).getName().equals(name))
                {
                    return ((Command) services[i]).execute(commandline);
                }
            }
            catch (Exception ex)
            {
                // Since the services returned by the tracker could become
                // invalid at any moment, we will catch all exceptions, log
                // a message, and then ignore faulty services.
                System.err.println(ex);
            }
        }
        return false;
    }

    public void shutdownApplication()
    {
        // Shut down the felix framework when stopping the
        // host application.
        try {
            m_felix.stop();
            m_felix.waitForStop(0);
        } catch(Exception ex) {

        }
    }
}