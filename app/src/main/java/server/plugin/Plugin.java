package server.plugin;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import shared.command_classes.Command;

/**
 * Created by eseamons on 4/11/2017.
 */

public class Plugin {
    private IDaoFactory factory;

    public Plugin(String implementation_type) {

    }

    private void loadDaoFactory() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
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

        factory = (IDaoFactory) Class.forName("plugin.ConcreteDaoFactory", false, uc).newInstance();

    }

    private void getJsonObject() {

    }


}
