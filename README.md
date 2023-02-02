[![](https://jitpack.io/v/com.meisterplan/detekt-rules.svg)](https://jitpack.io/#com.meisterplan/detekt-rules)

# Meisterplan Detekt Rules

This repo contains [Detekt](https://detekt.dev/) Rules useful for developing [Meisterplan](https://github.com/meisterplan).

## How to use it

Look up the [most recent release](https://github.com/meisterplan/detekt-rules/releases) and add the dependency to your `build.gradle.kts`:
```kotlin
dependencies {
    // ...
    detektPlugins("com.meisterplan:detekt-rules:VERSION")
}
```

The dependency can either be fetched from jitpack.io:
```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}
```

or from GitHub Packages:
```kotlin
repositories {
    // ...
    maven {
        url = uri("https://maven.pkg.github.com/meisterplan/detekt-rules")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}
```

If you want to configure the rules individually look at the [config.yml](https://github.com/meisterplan/detekt-rules/blob/main/src/main/resources/config/config.yml) and set the same flags in your local detekt config.

For `CopyOnDataClassWithNonPublicConstructor`, it is required to [run detekt with type resolution](https://detekt.dev/docs/gettingstarted/type-resolution#enabling-on-a-jvm-project).
