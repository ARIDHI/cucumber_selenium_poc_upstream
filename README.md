# cucumber_selenium_poc_upstream
 . src/test/resources/features - all the cucumber features files (files .feature ext) goes here.
 . src/test/java/userStepDefinition - you can define step defintion under this package for your feature steps.
 . src/test/java/env - this package contains cucumber runner (RunCukeTest.java) where you can configure your glue code location (step defintions), define test result   output format.(html, json, xml). Hooks where you can configure all before and after test settings Hooks.java, DriverUtil.java contains code for intializing driver 
 . mvn clear test : to run test localy 
 
