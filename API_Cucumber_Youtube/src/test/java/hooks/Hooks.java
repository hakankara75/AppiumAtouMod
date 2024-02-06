package hooks;

import basicUrl.Reqres_BaseUrl;
import io.cucumber.java.Before;


public class Hooks {



    @Before
    public void before(){
        Reqres_BaseUrl.setup();
    }
}
