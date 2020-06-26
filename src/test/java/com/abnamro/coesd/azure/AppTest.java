package com.abnamro.coesd.azure;

import static org.junit.Assert.assertTrue;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        Weld weld = new Weld();
        weld.addBeanClass(TestBean.class);
        WeldContainer container = weld.initialize();

        TestBean testBean = container.select(TestBean.class).get();
        testBean.test();
        assertTrue( true );
    }
}
