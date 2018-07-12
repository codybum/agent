package core;

import core.command.Command;
import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class HostApplication
{
    private HostActivator m_activator = null;
    private Felix m_felix = null;
    private ServiceTracker m_tracker = null;

    public HostApplication()
    {
        //System.setProperty("felix.fileinstall.dir", "/Users/cody/IdeaProjects/firstosgi/target/");
        //System.setProperty("felix.fileinstall.noInitialDelay", "true");
        //System.setProperty("felix.fileinstall.poll", "1000");

        System.out.println("Building OSGi Framework");

        // Create a configuration property map.
        Map configMap = new HashMap();
        // Export the host provided service interface package.
        configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                "sun.*,com.sun.*,javax.xml.*");
        configMap.put("org.osgi.framework.bootdelegation","sun.*,com.sun.*,javax.xml.*");


        // make sure the cache is cleaned
        configMap.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);

        //config.put(FRAMEWORK_SYSTEMPACKAGES_EXTRA, this.systemPackages.toString());

        // more properties available at: http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html
        configMap.put("ds.showtrace", "true");
        configMap.put("ds.showerrors", "true");

        configMap.put("felix.log.level","99");

        //port
        configMap.put("org.osgi.service.http.port", "8181");

        configMap.put("obr.repository.url","http://felix.apache.org/obr/releases.xml");

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

            //buildConfig();



            //m_felix.getBundleContext().installBundle("/Users/cody/IdeaProjects/firstosgi/firstosgi-1.0-SNAPSHOT.jar");
            BundleContext bc = m_felix.getBundleContext();

            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.log-1.0.1.jar").start();
            //bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/org.osgi.service.cm-1.6.0.jar").start();
            //Bundle loggerService = bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/pax-logging-service-1.10.1.jar");
            //Bundle loggerAPI = bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/pax-logging-api-1.10.1.jar");
            //loggerService.start();
            //loggerAPI.start();
            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.log-1.0.1.jar").start();
           // bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/logger-1.0-SNAPSHOT.jar").start();

            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.servlet-api-1.1.2.jar").start();


            installInternalBundleJars(bc,"org.osgi.service.cm-1.6.0.jar").start();
            Bundle loggerService = installInternalBundleJars(bc,"pax-logging-service-1.10.1.jar");
            Bundle loggerAPI = installInternalBundleJars(bc,"pax-logging-api-1.10.1.jar");
            loggerService.start();
            loggerAPI.start();
            installInternalBundleJars(bc,"org.apache.felix.http.servlet-api-1.1.2.jar").start();
            installInternalBundleJars(bc,"org.apache.felix.http.jetty-4.0.0.jar").start();
            installInternalBundleJars(bc,"jersey-all-2.22.1.jar").start();
            installInternalBundleJars(bc,"org.apache.felix.configadmin-1.9.2.jar").start();
            installInternalBundleJars(bc,"org.apache.felix.scr-2.1.0.jar").start();
            installInternalBundleJars(bc,"library-1.0-SNAPSHOT.jar").start();
            installInternalBundleJars(bc,"controller-1.0-SNAPSHOT.jar").start();


            ////bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.api-3.0.0.jar").start();
            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.jetty-4.0.0.jar").start();


            //bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/jersey-all-2.25.1.jar").start();

            //bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/t/jersey-all-2.22.1.jar").start();


            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.webconsole-4.3.4-all.jar").start();


            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.configadmin-1.9.2.jar").start();


            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.scr-2.1.0.jar").start();

            //bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/library-1.0-SNAPSHOT.jar").start();

            //bc.installBundle("file:/Users/cody/IdeaProjects/controller/target/controller-1.0-SNAPSHOT.jar").start();


            /*
            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.configadmin-1.9.2.jar").start();


            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.eventadmin-1.5.0.jar").start();

            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.servlet-api-1.1.2.jar").start();
            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.api-3.0.0.jar").start();


            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.http.jetty-4.0.0.jar").start();
            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.webconsole-4.3.4-all.jar").start();



            bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/org.apache.felix.scr-2.1.0.jar").start();






            bc.installBundle("file:/Users/cody/IdeaProjects/felix-framework-5.6.10/bundle/library-1.0-SNAPSHOT.jar").start();



            bc.installBundle("file:/Users/cody/IdeaProjects/controller/target/controller-1.0-SNAPSHOT.jar").start();

*/
            //bc.installBundle("file:/Users/cody/IdeaProjects/agent/jars/");

            //bc.installBundle("file:/Users/cody/IdeaProjects/firstosgi/target/firstosgi-1.0-SNAPSHOT.jar");
            //bc.installBundle("file:/Users/cody/IdeaProjects/firstosgi/target/firstosgi-1.0-SNAPSHOT.jar");
            //bc.installBundle("file:/Users/cody/IdeaProjects/aem-osgi-annotation-demo/core/target/osgi-annotation-demo.core-1.0-SNAPSHOT.jar");

            //buildConfig();

            for (Bundle bundle : m_activator.getBundles()) {
                if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {

                    //bundle.start();

                    /*
                    System.out.println(bundle.getState());
                    System.out.println(bundle.getBundleId());
                    System.out.println(bundle.getLocation());
                    System.out.println(bundle.getVersion());

                    System.out.println("---");
                    */
                }
            }


            //bc.installBundle("file:/Users/cody/IdeaProjects/firstosgi/target/firstosgi-1.0-SNAPSHOT.jar");
            //bc.installBundle("file:/Users/cody/IdeaProjects/firstosgi/firstosgi-1.0-SNAPSHOT.jar");


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

    private Bundle installInternalBundleJars(BundleContext context, String bundleName) {

        Bundle installedBundle = null;
        try {
            URL bundleURL = getClass().getClassLoader().getResource(bundleName);
            if(bundleURL != null) {

                String bundlePath = bundleURL.getPath();
                installedBundle = context.installBundle(bundlePath,
                        getClass().getClassLoader().getResourceAsStream(bundleName));


            } else {
                System.out.println("Bundle = null");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        if(installedBundle == null) {
            System.out.println("Failed to load bundle exiting!");
            System.exit(0);
        }

        return installedBundle;
    }


    private boolean startInternalBundleJars(Bundle bundle) {

        try {
            if(bundle != null) {
                int bundleState = bundle.getState();

                if (bundleState == 2) {
                    bundle.start();
                    bundleState = bundle.getState();
                    if (bundleState == 32) {
                        return true;
                    }
                } else {
                    System.out.println("bundle not ready");
                }
            } else {
                System.out.println("Bundle = null");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private String getState(int stateCode) {
        String returnString = null;

        switch (stateCode) {
            case 1:  returnString= "Uninstalled";
                break;
            case 2:  returnString= "Installed";
                break;
            case 4:  returnString= "Resolved";
                break;
            case 8:  returnString= "Starting";
                break;
            case 16:  returnString= "Stopping";
                break;
            case 32:  returnString= "Active";
                break;
            default: returnString = "Unknown";
                break;
        }
        return returnString;
    }

    public void printb() {
        for (Bundle bundle : m_activator.getBundles()) {
            if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
                System.out.println("state:" + getState(bundle.getState()));
                System.out.println("id:" + bundle.getBundleId());
                System.out.println("location:" + bundle.getLocation());
                System.out.println("version:" + bundle.getVersion());

                System.out.println("---");
            }
        }
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