package hooks;

import io.cucumber.java.Before;

import static baseUrl.Herokuapp_BaseUrl.setupHerokuapp;
import static baseUrl.JsonPlaceHolder_BaseUrl.setUpJsonPlaceHolder;
import static baseUrl.Reqres_BaseUrl.setupReqres;

public class Hooks {

@Before
    public void beforeMethod(){

    //setUpJsonPlaceHolder();
    //setupReqres();
    setupHerokuapp();




}



}
