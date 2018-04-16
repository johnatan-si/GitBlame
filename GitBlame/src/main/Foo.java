package main;
import java.io.*;
import java.util.*;

public class Foo {
    public static void main(String[] args) throws Exception {
        // Run command and wait till it's done
        Process p ;
        
        p = Runtime.getRuntime().exec("cd /home/johnatan/Test/angularjs-eclipse/org.eclipse.angularjs.core/src/org/eclipse/angularjs/core/");
        p.waitFor();
        
        p = Runtime.getRuntime().exec("git blame -l ScriptsFolder.java");
        p.waitFor();
        
     

        // Grab output and print to display
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}