**Deprivatizer** is a simple Java compiler plugin that makes all private fields package-private so [ReflectASM](/EsotericSoftware/reflectasm) can access them. Rough initial version. TODO: Testing, Gradle/Ant integration.

Compile and pack the plugin:

```sh
gradle build
```

Compile and pack the plugin without gradle:

```sh

# first delete .DS_Store files if you're on a Mac
find . -name .DS_Store -delete

mkdir jar
cp -r src/main/resources/META-INF jar
javac -cp $JAVA_HOME/lib/tools.jar -sourcepath src/main/java -d jar src/main/java/com/example/deprivatizer/Deprivatizer.java
jar cf deprivatizer.jar -C jar .
```

Use the plugin during compilation:

```sh
javac -cp deprivatizer.jar -sourcepath src/test/java -d target/classes src/test/java/Test.java
java -cp target/classes Test
```
