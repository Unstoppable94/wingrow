
main class: com.winhong.jetty.App

before run your application,please:
* check setting in  WinhongInner.json 
* add "/src/main/resources/WinGrow/config/WinhongInner.json" in your .gitignore

Maven compile command please use:
* default compile: clean package -DskipTests 
* build jar : clean compile assembly:single
*  mvn install -D maven.javadoc.skip=true -DskipTests
* mvn install -D maven.javadoc.skip=true -DskipTests deploy -e
