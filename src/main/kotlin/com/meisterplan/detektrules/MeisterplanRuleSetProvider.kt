package com.meisterplan.detektrules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class MeisterplanRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "meisterplan"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                CopyOnDataClassWithNonPublicConstructor(config),
            ),
        )
    }
}
