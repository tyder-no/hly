package no.ssb.health.hly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ParametricRegularExpressionTest.class,
      RegularExpressionJUnit4Test.class,
      AppTest.class})

public class JUnit4Suite {

}
