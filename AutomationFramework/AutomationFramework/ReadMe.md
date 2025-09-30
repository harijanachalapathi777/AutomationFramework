Framework insights:
------------------
1. Number of pages :

2. What to automate : 

3. Types of tests :
    - Sanity/Smoke [P1]- 
    - Regression [P3]- 
    - Data-driven [P2] - 
    - Other [P4] - 
    
4. Design & Development of framework :

5. Execution :
    - Local - 
    - Remote - 

6. Maintenance :

-----------------------------------
WebDriver:

A centralized Abstract webdriver is created using DriverFactory Design Pattern
This allows us to dynamically initializes drivers based on configuration(be it local or remote execution).

how? - Driver factory class will read 'browser'and 'runMode' from config.property file or Env variables.
       Based on these, it sets up either a local driver using WebDriverManager or a remote driver using 
       Selenium Grid via RemoteWebDriver.
       
       To support parallel execution, especially in CI/CD pipelines, I use ThreadLocal<WebDriver> to ensure 
       thread safety. Each test thread gets its own isolated driver instance, which prevents interference and 
       enables scalable parallel testing
       
       The driver is initialized in a BaseTest class using @BeforeMethod, and cleaned up in @AfterMethod. Test 
       classes extend BaseTest, and retrieve the driver via DriverFactory.getDriver(), keeping the test code clean 
       and decoupled from setup logic.



    