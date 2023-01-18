# Meisterplan Detekt Rules

This repo contains [Detekt](https://detekt.dev/) Rules useful for developing [Meisterplan](https://github.com/meisterplan).

## How to use it

Look up the [most recent version](todo) and add the dependency to your `build.gradle.kts`:
```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // ...
    detektPlugins("com.meisterplan.detektrules:detekt-rules:VERSION")
}
```

And configure your detekt configuration (usually `detekt-overrides.yml`) by enabling the desired rules:
```yaml
meisterplan:
  CopyOnDataClassWithNonPublicConstructor:
    active: true
  # ...
```
