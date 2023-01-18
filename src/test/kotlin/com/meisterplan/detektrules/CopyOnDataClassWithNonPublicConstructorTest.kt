package com.meisterplan.detektrules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.kotest.matchers.collections.shouldHaveSize
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class CopyOnDataClassWithNonPublicConstructorTest(private val env: KotlinCoreEnvironment) {

    @Test
    fun `reports private data copy`() {
        val code = """
        @Suppress("DataClassPrivateConstructor")
        data class A private constructor(val i: Int)

        val x = A(3)
        val y = x.copy(i = 4)
        """
        val findings = CopyOnDataClassWithNonPublicConstructor(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 1
    }

    @Test
    fun `doesn't report public data copy`() {
        val code = """
        data class A(val i: Int)

        val x = A(3)
        val y = x.copy(i = 4)
        """
        val findings = CopyOnDataClassWithNonPublicConstructor(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 0
    }
}
