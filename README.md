**Deprivatizer** is a simple Java compiler plugin that makes all private fields package-private so [ReflectASM](/EsotericSoftware/reflectasm) can access them.

1. [Gradle](#gradle)
2. [Ant](#ant)
3. [Shell](#shell)

## Gradle

Compile and pack `deprivatizer` with Gradle:

```sh
gradle build
```

Run `deprivatizer-test` with Gradle:

```sh
gradle run
```

To use the plugin with other Gradle projects, make `deprivatizer` a sibling project and add the following to `build.gradle` of your project:

```gradle
dependencies {
  compile project(':deprivatizer')
}
```

Alternatively, publish `deprivatizer.jar` to a repository and add the appropriate dependency to `build.gradle`.

## Ant

Compile and pack `deprivatizer` with Ant:
```sh
cd deprivatizer
ant
```

Run `deprivatizer-test` with Ant:

```sh
cd deprivatizer-test
ant
```

To use the plugin with other Ant projects, copy `deprivatizer.jar` to a convenient location and add it to the compiler classpath in `build.xml` of your project:

```ant
<javac ... >
  <classpath location=".../deprivatizer.jar" />
</javac>
```

## Shell

Compile and pack `deprivatizer` with shell and JDK tools:

```sh
# first delete .DS_Store files if you're on a Mac
find . -name .DS_Store -delete
```

```sh
cd deprivatizer
mkdir -p build/jar
cp -r src/main/resources/META-INF build/jar
javac -cp "$JAVA_HOME/lib/tools.jar" -sourcepath src/main/java -d build/jar src/main/java/com/egis/deprivatizer/Deprivatizer.java
jar cf build/libs/deprivatizer.jar -C build/jar .
```

Run `deprivatizer-test` with shell and JDK tools:

```sh
cd deprivatizer-test
mkdir -p build/classes/main
javac -cp ../deprivatizer/build/libs/deprivatizer.jar -sourcepath src/main/java -d build/classes/main src/main/java/Test.java
java -cp build/classes/main Test
```
