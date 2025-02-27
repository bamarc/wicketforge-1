/*
 * Copyright 2010 The WicketForge-Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicketforge.codeInsight;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.impl.GutterIconTooltipHelper;
import com.intellij.codeInsight.daemon.impl.PsiElementListNavigator;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 */
class NavigableLineMarkerInfo {
    private NavigableLineMarkerInfo() {
    }

    public static LineMarkerInfo create(@NotNull PsiElement element, @NotNull final NavigatablePsiElement[] targets, @NotNull Icon icon) {
        return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL,
                new Function<PsiElement, String>() {
                    @Override
                    public String fun(PsiElement psiElement) {
                        return GutterIconTooltipHelper.composeText(targets, "", "{0}");
                    }
                },
                new GutterIconNavigationHandler() {
                    @Override
                    public void navigate(MouseEvent e, PsiElement elt) {
                        PsiElementListNavigator.openTargets(e, targets, "Select Target", null, new DefaultPsiElementCellRenderer());
                    }
                },
                GutterIconRenderer.Alignment.LEFT);
    }
}
