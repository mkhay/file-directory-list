# qoh-file-directory-list
List files and directories through a REST API and a CLI.

# Pre-requisite
* Java 8 OpenJDK
* Maven 3.6 `https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/`
* Make sure `JAVA_HOME` is set properly

# Run locally
**1. Clone the repository** 
  ```json 
  git clone git@github.com:mkhay/file-directory-list.git
  ```
  OR
  ```json 
  git clone https://github.com/mkhay/file-directory-list.git
  ```
**2. Navigate to the project root directory** 
  ```shell
  cd file-directory-list
  ```
**3. Build the application from source** 
  ```shell
  mvn clean install -DskipTests
  ```
**4. Run the application  (REST API mode)**
  ```shell
  java -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar
  ```
**5. Test** 
  ```shell
  http://localhost:8080/v1/folders/{folder_name}
  ```
  **<em>Example</em>**
  ```shell
  http://localhost:8080/v1/folders/src
  ```

**6 .Run the application (CLI mode)** 
  ```shell
  java -Dspring.main.web-application-type=none -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar {folder_name}
  ```
  **<em>Example</em>**
  ```
  java -Dspring.main.web-application-type=none -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar src
  ```
> Note: for now, the application only looks for directories under the current working directory.

# Pending items for Production
* Currently, no active profile is set (using default), add prod profile.
* Enable security and provide authentication and authorization on the REST API(s) e.g. basic auth, JWT token, LDAP integration etc.
* Protect the REST API from DoS attack, and implement rate limiting e.g. Bucket4j.
* Set up a domain name, and enable HTTPs.
* Validate user input.
* Add support for special file types e.g. socket file, device file etc.
* Add trace logs to be enabled on demand during prod investigation.
* Compress rotated logs on the server, and ship them to a different log server e.g. ELK.
* Build a CI/CD pipeline to build, package and deploy the service to different environment, including production.
* Create a local system user on the target server for the application to run with, with limited permission.
* Expose health end point(s) to monitor the application health e.g. availability, memory etc.
* For Unix based system, handle symlinks to detect an infinite loop when symlink point back to a parent directory.
* Add native OS CLI command version e.g. picocli or shell.
* Add unit test(s) and integration test(s) e.g. ZeroCode, Cucumber etc.
* Describe REST APIs and generate documentation e.g. Swagger.
* Add release notes.
