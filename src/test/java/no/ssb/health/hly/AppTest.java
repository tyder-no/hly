package no.ssb.health.hly;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import org.junit.BeforeClass;
import org.junit.Test ;
import static org.junit.Assert.assertTrue ;
import static org.junit.Assert.assertFalse ;

import java.io.* ;

/**
 * Unit test for simple App.
 */
public class AppTest 
//    extends TestCase
{
	//protected int value1, value2;
	static int value1, value2;
	
	   // assigning the values
	//@Override
	@BeforeClass
	   public static void setUp(){
	      value1=3;
	      value2=3;
	   }
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    //public AppTest( String testName )
	public AppTest()
    {
       // super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    /*
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
*/
    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
    	File file = new File("src/resources/data");
        String[] flist = file.list();
        System.out.println(flist[0]);
        assertTrue( true );
        double result= value1 + value2;
        assertTrue(result == 6);
        value1 = value1 + value2;
        System.out.println("testApp value1: "+value1);
    }
    
    @Test
    public void testApp1()
    {
    	File file = new File("src/resources/data");
        String[] flist = file.list();
        System.out.println(flist[1]);
        assertFalse( false );
        System.out.println("testApp1 value1: "+value1);
        System.out.println("testApp1 result: "+(value1+value2));
        double result= value1 + value2;
        assertTrue(result == 9);
        
    }
}
