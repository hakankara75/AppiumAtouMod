@get
  Feature: get
    Scenario: get JsonPath
      Given jsonplaceholder sitesine gidilir
      And get istegi yapilir
      Then body assert edilir