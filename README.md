## Sample Wicket application with Spring Boot integration

Minimal wicket application with Spring Boot configuration 

###Requirements

* wicket 7.4.0
* spring boot 1.4.0.RELEASE
* java 1.8
* maven 3.3.9

###Configuration

**1. include spring boot dependencies (`pom.xml`)**

it's your choice how to include the spring boot dependencies (with parent pom or with import scope)!
please see the spring boot docs [Getting started maven installation](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-maven-installation)

```
<!-- SPRING BOOT DEPENDENCIES -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>${spring.boot.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>

<!-- SPRING BOOT STARTER WEB -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>${spring.boot.version}</version>
</dependency>
```

**2. WebInitializer**

We need to register the Wicket Filter.
The WebInitializer class is a replacement of the traditional `web.xml` with `ServletContextInitializer` interface to configure a Servlet 3.0+ servlet context.

```java
final FilterRegistration filter = servletContext.addFilter("wicket-filter", WicketFilter.class);
filter.setInitParameter("applicationClassName", "de.egg.samples.SampleApplication");
filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
filter.addMappingForUrlPatterns(null, false, "/*");
filter.setInitParameter("configuration", "development");
```

**3. Enable Spring Boot Application**
Mark the application class with `@SpringBootApplication` annotation and add the main class.

```java
public static void main(final String[] args) throws Exception {
   SpringApplication.run(SampleApplication.class, args);
}
```    

**4. Usage of @SpringBean annotations**
If you want to use the @SpringBean annotations follow the steps

*1. add wicket spring dependencies*

```
<!-- WICKET SPRING DEPENDENCIES-->
<dependency>
    <groupId>org.apache.wicket</groupId>
    <artifactId>wicket-spring</artifactId>
    <version>${wicket.version}</version>
</dependency>
```        

*2. Enable spring injections in the application class (init method)*

```java
// spring injector for @SpringBean annotations
getComponentInstantiationListeners().add(new SpringComponentInjector(this));
```

*3. usage*

Create the YourService.class with `@Service` annotation

Use the Service in the page / panel
```java
public class SampleHomePage extends WebPage {

    /**
     * {@link ...YourService}.
     */
    @SuppressWarnings("unused")
    @SpringBean(name = "yourService")
    private YourService yourService;

    /**
     * @param parameters {@link org.apache.wicket.request.mapper.parameter.PageParameters}
     */
    public SampleHomePage(final PageParameters parameters) {
        super(parameters);

        yourService.doSomething();
    }
}
```



 
**5. Run the Application**

maven spring boot plugin is included in `pom.xml`

`mvn spring-boot:run`

---
**_Keep it simple and enjoy._**