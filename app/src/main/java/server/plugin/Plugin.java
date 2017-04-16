package server.plugin;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by eseamons on 4/11/2017.
 */

public class Plugin {
    private Class<?> factoryClass;

    public Plugin(String implementationType) {

    }

    private void loadDaoFactory(String implementationType) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        File plugins = new File("plugins");
        File[] jars = plugins.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".jar");
            }
        });

        URL[] urls = new URL[jars.length];
        for (int i=0; i<jars.length; i++) {
            urls[i] = jars[i].toURI().toURL();
        }
        ClassLoader uc = new URLClassLoader(urls,this.getClass().getClassLoader());

        factoryClass = Class.forName("plugin.ConcreteDaoFactory", false, uc);

    }

    public IDaoFactory createDaoFactory() {
        try {
            Constructor<?> con = factoryClass.getConstructors()[0];
            return (IDaoFactory) con.newInstance();
        } catch(Exception e) {
            return null;
        }
    }

    private void getJsonObject() {

    }


}
