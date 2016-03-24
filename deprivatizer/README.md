**Deprivatizer** is a simple Java compiler plugin that makes all private fields package-private so [ReflectASM](/EsotericSoftware/reflectasm) can access them. Rough initial version. TODO: Testing, Gradle/Ant integration.

Compile and pack the plugin:

```sh
javac -cp $JAVA_HOME/lib/tools.jar -sourcepath src/main/java -d jar src/main/java/com/example/deprivatizer/Deprivatizer.java
find . -name .DS_Store -delete
jar cfM deprivatizer.jar -C jar .
```

Use the plugin during compilation:

```sh
javac -cp deprivatizer.jar -sourcepath src/test/java -d target/classes src/test/java/Test.java
java -cp target/classes Test
```