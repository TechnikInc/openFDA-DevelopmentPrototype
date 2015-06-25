package gov.fda.web.controller;

import static junit.framework.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WelcomeConrollerTest {
	
	 private WelcomeController controller;

	    @Before
	    public void setUp() {
	    	
	        controller = new WelcomeController();
	    }

	    @Test
	    public void showHomePage() {
	    	Map<String, Object> model = new HashMap<String, Object>();
	        String view = controller.index(model);
	        assertEquals(WelcomeController.START_PAGE, view);
	    }

}
