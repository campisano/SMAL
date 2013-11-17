SMAL
====

Projeto de Engenharia de Software CEFET-RJ





### Test and Run

<pre>
mvn clean test * to run test
mvn tomcat:run * to run web server on http://localhost:8080/smal/
</pre>



##### Derby (embedded db) SQL EDITOR:

<pre>
use squirrel http://db.apache.org/derby/integrate/SQuirreL_Derby.html
copy derby.....jar in lib path
</pre>



##### Vademecum

<pre>
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DgroupId=br.cefet-rj.smal -DartifactId=smal -Dversion=0.1 -DinteractiveMode=false
cd smal/
ls
 pom.xml  src
mkdir -p src/main/java src/test/java
 ...
mkdir src/main/webapp/META-INF
nano src/main/webapp/META-INF/persistence.xml
</pre>



##### References
<pre>
 http://www.campisano.org/wiki/en/Struts2Spring3Hibernate4
 http://search.maven.org/#search
 http://www.ibm.com/developerworks/library/wa-datawebapp/
 http://blog.wolfgang-werner.net/restful-http-with-jax-rs/
 http://www.javacodegeeks.com/2012/05/tutorial-hibernate-jpa-part-1.html
 http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
 http://www.mkyong.com/webservices/jax-rs/jersey-spring-integration-example/
 http://learn.knockoutjs.com/#/?tutorial=loadingsaving
</pre>
