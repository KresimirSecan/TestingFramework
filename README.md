
This project is a simple framework for automated testing of the [WebFarma website](https://github.com/KresimirSecan/WebFarma).

The framework is written in **Java** and uses **Maven** for dependency management. We use **Selenium WebDriver** for test automation, while **ChromeDriver** is used to execute tests in the **Google Chrome** browser. **TestNG** is used for generating test reports.

---
**If you do not want to set up the site and framework on your machine and just want to see how the framework works, at the end of the readme is speed up video of the testing framework in action.**
## Requirements
Before setting up the project, ensure you have the following tools installed:
### 1. WebFarma
- **First we need to set up and run the WebFarma app locally** 
  - [WebFarma Set Up](https://github.com/KresimirSecan/WebFarma/blob/master/README.md)
### 2. Development Tools:
- **Java JDK** (Java Development Kit)  
  - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Recommended: IntelliJ IDEA Community Edition**  
  - [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download)

### 3. WebDriver:
- **Google Chrome Browser**  
  - [Download Chrome](https://www.google.com/chrome/)
- **ChromeDriver** (version must match your Chrome browser version)  
  - [Download ChromeDriver](https://developer.chrome.com/docs/chromedriver/downloads/version-selection)

### 4. Build Tools & Dependencies:
- **Apache Maven**  
  - [Download Maven](https://maven.apache.org/download.cgi)
  - If Maven is not added to the system path, extract the binaries and manually reference   their location when running tests.

---
## Test Execution Guide

1. **Clone the repository**:
   ```sh
   git clone https://github.com/KresimirSecan/TestingFramework.git
   ```

2. **Navigate to the project directory in cmd**:
   ```sh
   cd "ProjectFolderLocation"
   ```

3. **Run tests using Maven**:
   ```sh
   "MavenPackageLocation\apache-maven-3.9.9-bin\apache-maven-3.9.9\bin\mvn" test
   ```

4. **View test reports**:
   - Open the **index.html** file in your browser:
     ```
     ProjectFolderLocation\target\surefire-reports\index.html
     ```
---



https://github.com/user-attachments/assets/c6c39a27-7325-4f19-bd59-d2f718dd8db0



