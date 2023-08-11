## Prerequisites ##

* Have [java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed
* Have [maven](http://maven.apache.org/) installed


Running test
--------------
Go to your project directory from terminal and hit following commands


- Default run, on chrome **mvn clean verify**



Reports and Screenshots
--------------
* **reports/report.html** - the report of the execution. Wait for approx 1 min after execution for the complete report.
* **reports/screenshots/** - folder which contains all the screenshots taken during execution. Can be configured from the Config.properties file

How to navigate or view the report?
--------------
* Open the reports/report.html file in a browser

* There will be 3 tabs (in case of failures 4) in the left hand side of the report below the 'Extent' blue icon.

* The first tab shows the list of scenario with the steps and the individual status of each step'

* The last tab shows the overview of the execution including, time of execution, total scenario/steps, time taken, details of system on which the scripts were executed.


OS and Browser Support
--------------
* The project currently supports the below drivers:
    * **Chrome** (both Windows and Mac)
    * **Firefox**(both Windows and Mac)
    * **Edge** (both Windows and Mac)
* More driver supports can be added if required
* The project already has the code for Edge and Firefox support.
* The project also has the code to run the test case in headless mode in chrome and firefox. Set the name of browser in Config.properties file to chromeheadless or firefoxheadless to run the script in headless mode.
* By default the driver is set to **chrome**. The DriverUtil.java class can be updated to handle the new drivers. Then the Config.properties file can be updated to select the driver of choice

**Browser resolution**
* The browser resolution can be set in the Config.properties file window.height and window.width. Set the value of window.maximize to false to change the resolution.
* By default the window.maximize is set to true



Additional Features
------------------

**Highlight element during execution**

* The project provides a feature to highlight an element during the execution
* Set the property highlight.element as true in the Config.properties if you wish to see the elements highlighted during execution. By default the value is set to false.

**Create Separate reports for each execution**

* The project gives a feature to create separate reports file for each execution in the project folder.
* Set the property reports.history as true in the Config.properties if you wish to create separate reports after each execution. By default it is set as false.


#selenium-cucumber-java-maven

selenium-cucumber : Automation Testing Using Java

selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web.
It enables you to write and execute automated acceptance/unit tests.
It is cross-platform, open source and free. [More Details](http://seleniumcucumber.info/)


* **src/test/resources/features** - all the cucumber features files (files .feature ext) goes here.
* **src/test/java/bddTest/pages** - you can define individual methods for performing actions on elements for each page under this package. The pages created under this package can be used in the step definition classes
* **src/test/java/bddTest/steps/definitions** - you can define step definition under this package for your feature steps.
* **src/test/java/bddTest** - this package contains cucumber runner (TestRunner.java) where you can configure your glue code location (step definitions), define test result output format.(html, json, xml). Hooks where you can configure all before and after test settings Hooks.java, DriverUtil.java contains code for initializing driver instances for respective driver.

Writing a test
--------------

The cucumber features goes in the `features` library and should have the ".feature" extension.

You can start out by looking at `features/HomePage.feature`. You can extend this feature or make your own features.

