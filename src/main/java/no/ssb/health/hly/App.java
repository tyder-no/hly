package no.ssb.health.hly;

import java.io.* ;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        File file = new File("src/resources/data");
        String[] flist = file.list();
        for (int i=0 ; i<flist.length;i++ )
             System.out.println(flist[i]);
        
        
    }
}
