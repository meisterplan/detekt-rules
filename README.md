[![](https://jitpack.io/v/meisterplan/detekt-rules.svg)](https://jitpack.io/#meisterplan/detekt-rules)

# Meisterplan Detekt Rules

This repo contains [Detekt](https://detekt.dev/) Rules useful for developing [Meisterplan](https://github.com/meisterplan).

## How to use it

Look up the [most recent release](https://github.com/meisterplan/detekt-rules/releases) and add the dependency to your `build.gradle.kts`:
```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // ...
    detektPlugins("com.meisterplan:detekt-rules:VERSION")
}
```

If you want to configure the rules individually look at the [config.yml](https://github.com/meisterplan/detekt-rules/blob/main/src/main/resources/config/config.yml) and set the same flags in your local detekt config.
