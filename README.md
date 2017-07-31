# agile-doc-libs

A demo project showing some libraries that can be used for publishing documentation

To build, test & generate documentation run:

```
mvn install
```

To publish the maven site run:

```
mvn site
```

To just run the service without testing run:

```
mvn liberty:run-server -DskipTests
```

The aspectj weaver jar path has been hard-coded in this file:

```
jvm.options
```

Replace it with the path to this jar on your own machine