# qoh-file-directory-list
List files and directories through a REST API and a CLI.

# Pre-requisite
* Java 8 OpenJDK
* Maven 3.6 `https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/`
* Make `JAVA_HOME` is set properly

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
