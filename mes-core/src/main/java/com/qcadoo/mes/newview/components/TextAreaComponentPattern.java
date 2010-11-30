package com.qcadoo.mes.newview.components;

import com.qcadoo.mes.newview.AbstractComponentPattern;
import com.qcadoo.mes.newview.ComponentPattern;
import com.qcadoo.mes.newview.ComponentState;
import com.qcadoo.mes.newview.ViewComponent;

@ViewComponent("textarea")
public class TextAreaComponentPattern extends AbstractComponentPattern {

    public TextAreaComponentPattern(final String name, final String fieldPath, final String sourceFieldPath,
            final ComponentPattern parent) {
        super(name, fieldPath, sourceFieldPath, parent);
    }

    @Override
    public ComponentState getComponentStateInstance() {
        return new TextInputComponentState();
    }

    @Override
    public String getJspPath() {
        return JSP_PATH;
    }

    @Override
    public String getJavaScriptFilePath() {
        return JS_PATH;
    }
}
