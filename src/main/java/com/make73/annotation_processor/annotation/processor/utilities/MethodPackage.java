package com.make73.annotation_processor.annotation.processor.utilities;

import com.make73.annotation_processor.annotation.ExposeMethod;

import javax.lang.model.element.ExecutableElement;

public class MethodPackage {
    private ExecutableElement executableElement;
    private ExposeMethod exposeMethod;

    public MethodPackage(ExecutableElement executableElement, ExposeMethod exposeMethod) {
        this.executableElement = executableElement;
        this.exposeMethod = exposeMethod;
    }

    public ExecutableElement getExecutableElement() {
        return executableElement;
    }

    public ExposeMethod getExposeMethod() {
        return exposeMethod;
    }
}
