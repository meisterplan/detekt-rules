package com.meisterplan.detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import io.gitlab.arturbosch.detekt.rules.fqNameOrNull
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.psiUtil.getReceiverExpression
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope

@RequiresTypeResolution
class CopyOnDataClassWithNonPublicConstructor(config: Config) : Rule(config) {

    override val issue = Issue(
        javaClass.simpleName,
        Severity.CodeSmell,
        "This rule reports calls of copy() on a data class that has a non-private constructor. This can lead to correctness issues, since copy()" +
                " is always public and allows to bypass the constructor.",
        Debt.TEN_MINS,
    )

    override fun visitCallExpression(expression: KtCallExpression) {
        super.visitCallExpression(expression)
        val context = expression.context
        val calleeExpression = expression.calleeExpression
        if (context is KtDotQualifiedExpression
            && calleeExpression is KtNameReferenceExpression
            && calleeExpression.getReferencedName().lowercase() == "copy"
        ) {
            val type = bindingContext.getType(expression)
            val typeMemberScope = type?.memberScope
            if (typeMemberScope is LazyClassMemberScope
                && typeMemberScope.getPrimaryConstructor()?.visibility != DescriptorVisibilities.PUBLIC
            ) {
                report(
                    CodeSmell(
                        issue, Entity.from(expression), "Non-public constructed data class '${calleeExpression.getReceiverExpression()?.text ?: ""}'"
                                + " of type '${type.fqNameOrNull()}' should not bypass constructor by calling copy()."
                    )
                )
            }
        }

    }
}
