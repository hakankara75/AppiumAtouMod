@postTestData
  Feature: post test data
    Scenario: post test data
      Given kullanici herokuapp sitesine gider
      And kullanici herokuapp sitesine post islemi yapar
      Then kullanici herokuapp sitesinden gelen response dogrula