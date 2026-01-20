plugins {
    application
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("org.example.lisp.interpreter.Interpreter")
}

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
