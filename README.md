# qoh-file-directory-list
List files and directories through a REST API and a CLI.

# Pre-requisite
* Java 8 OpenJDK
* Maven 3.6 `https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/`
* Make `JAVA_HOME` is set properly

# Run locally
* Clone the repository 
  ```json 
  git clone git@github.com:mkhay/file-directory-list.git
  ```
  OR
  ```json 
  git clone https://github.com/mkhay/file-directory-list.git
  ```
* Navigate to the project root directory 
  ```shell
  cd file-directory-list
  ```
* Build the application from source 
  ```shell
  mvn clean install -DskipTests
  ```
* Run the application 
  ```shell
  java -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar
  ```
* Run from your Browser 
  ```shell
  http://localhost:8080/v1/folders/{folder_name}
  ```
  **Example**
  ```shell
  http://localhost:8080/v1/folders/src
  ```
  
* Run with CLI 
  ```shell
  java -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar {folder_name}
  ```
  **Example**
  ```
  java -jar target/qoh-file-directory-list-0.0.1-SNAPSHOT.jar src
  ```
> Note: for now, the application only looks for directories under the current working directory.
