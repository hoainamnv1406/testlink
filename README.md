## Hollywood bowl - Automation project
Built on Serenity and Cucumber 
### The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                       Test runners and supporting code
      + elements                 Element Base file
      + locators                 Element locators files
      + pages                    Page functions files
      + step                     Step files
    + resources
      + drivers                  Driver files
        + mac
        + win
      + features                  Feature files
      + profiles                  Profile files
```

Serenity 2.2.13 introduced integration with WebdriverManager to download webdriver binaries.

## Executing the tests
To run the sample project, you can either just run the `TestRunner` test runner class, or run either `mvn verify` or `gradle test` from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Ddriver=firefox
```
Or
```json
$ gradle clean test -Pdriver=firefox
```

The test results will be recorded in the `target/site/serenity` directory.

## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the `pom.xml` file causes the reports to be generated automatically once all the tests have completed when you run `mvn verify`?

```
             <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.  
### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
        driver = chrome
        capabilities {
//    browserName = "firefox"
        acceptInsecureCerts = true
        "goog:chromeOptions" {
        args = ["test-type", "no-sandbox", "ignore-certificate-errors", "--window-size=1000,800",
        "incognito", "disable-infobars", "disable-gpu", "disable-default-apps", "disable-popup-blocking"]
        }
        "moz:firefoxOptions" {
        args = ["-headless"],
        prefs {
        "javascript.options.showInConsole": false
        },
        log {"level": "info"},
        }
        }
        }

        drivers {
        windows {
        webdriver.chrome.driver = src/test/resources/webdriver/windows/chromedriver.exe
        webdriver.gecko.driver = src/test/resources/webdriver/windows/geckodriver.exe
        }
        mac {
        webdriver.chrome.driver = src/test/resources/webdriver/mac/chromedriver
        webdriver.gecko.driver = src/test/resources/webdriver/mac/geckodriver
        }
        linux {
        webdriver.chrome.driver = src/test/resources/webdriver/linux/chromedriver
        webdriver.gecko.driver = src/test/resources/webdriver/linux/geckodriver
        }
        }
```

```json
$ mvn clean verify -Dwebdriver.driver=firefox


```

Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.

### Environment-specific configurations
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, __dev__, _staging_ and _prod_, with different starting URLs for each:
```java
environments {
  default {
  webdriver.base.url = "https://www.hollywoodbowl.co.uk/"
}
dev {
  webdriver.base.url = "https://www.hollywoodbowl.co.uk/"
}
staging {
  webdriver.base.url = "https://www.hollywoodbowl.co.uk/"
}
prod {
  webdriver.base.url = "https://www.hollywoodbowl.co.uk/"
}
}
```

You use the `environment` system property to determine which environment to run against. For example to run the tests in the staging environment, you could run:
```json
$ mvn clean verify -Denvironment=staging


```

### Report

```java
Report file location: target/site/serenity/index.html

```

### Execute with tags
#### Example #1: Execute only @smoke scenario
```json
$ mvn clean verify -Dcucumber.filter.tags="@smoke"

```
It will execute only one scenario which is tagged with @smoke and ignore all other scenarios.

#### Example #2: Skip or Ignore scenarios which are tagged with @manual
```json
$ mvn clean verify -Dcucumber.filter.tags="not @manual"

```
This will execute 3 scenarios out of 4, all 3, except the @manual one.

#### Example #3: Ignore or Skip multiple tags
```json
$ mvn clean verify -Dcucumber.filter.tags="not @manual"

```
This will execute 2 test scenarios excluding @manual and @sanity. Can you try by yourself by replacing @sanity with @regression that how many scenarios executed?

#### Example #4: Execute @regression tag ignoring @sanity

```json
$ mvn mvn verify -Dcucumber.filter.tags="@regression and not @sanity"

```
This will execute 2nd test scenario, 3rd scenario will be ignored, since it has @sanity tag. These are some example, in real world you might have to use different combination as well.

