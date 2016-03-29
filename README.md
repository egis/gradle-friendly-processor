**Deprivatizer** is a simple Java compiler plugin that makes all private fields package-private so [ReflectASM](/EsotericSoftware/reflectasm) can access them. Rough initial version. TODO: Testing, Gradle/Ant integration.

Compile and pack the plugin with Gradle:

```sh
gradle build
```

Run ```deprivatizer-test``` with Gradle:

```sh
gradle run
```

To use the plugin with Gradle, make ```deprivatizer``` a sibling project and add the following to ```build.gradle``` of your project:

```gradle
dependencies {
  compile project(':deprivatizer')
}
```

Compile and pack the plugin with Ant:
```sh
cd deprivatizer
ant
```

Compile and pack the plugin only with shell and JDK tools:

```sh

# first delete .DS_Store files if you're on a Mac
find . -name .DS_Store -delete

mkdir jar
cp -r src/main/resources/META-INF jar
javac -cp $JAVA_HOME/lib/tools.jar -sourcepath src/main/java -d jar src/main/java/com/example/deprivatizer/Deprivatizer.java
jar cf deprivatizer.jar -C jar .
```

Use the plugin only with shell and JDK tools:

```sh
javac -cp deprivatizer.jar -sourcepath src/test/java -d target/classes src/test/java/Test.java
java -cp target/classes Test
```
