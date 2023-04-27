# cucumber_selenium_poc_upstream
src/test/resources/features - all the cucumber features files (files .feature ext) goes here.
src/test/java/userStepDefinition - you can define step defintion under this package for your feature steps.
src/test/java/env - this package contains cucumber runner (RunCukeTest.java) where you can configure your glue code location (step defintions), define test result output format.(html, json, xml). Hooks where you can configure all before and after test settings Hooks.java, DriverUtil.java contains code for intializing driver instances for respective driver.
src/main/java/platformConfigs - If you want to run your test on saucelab and browserstack platforms, you need to add its configuration such as username, access key here.
src/main/java/browserConfig - When you run your test on remote browser/platform you have to provide capabilities and platform information here.
src/main/java/appUnderTest - If you are testing mobile based application you can keep your app build here.
