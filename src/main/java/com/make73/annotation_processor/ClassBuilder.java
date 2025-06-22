package com.make73.annotation_processor;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;
import com.make73.annotation_processor.annotation.processor.utilities.MethodPackage;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassBuilder {
    private String className;

    ExposeClass exposeClass;
    private String controllerClassName;

    private final List<MethodPackage> methods = new ArrayList<>();

    public void setClassName(String className, ExposeClass exposeClass) {
        this.className = className;
        this.exposeClass = exposeClass;
        // even if user enters space it is handled
        this.controllerClassName = String.join("", exposeClass.link().split(" ")) + "Controller";
    }

    public void addMethod(ExecutableElement method, ExposeMethod exposeMethod) {
        methods.add(new MethodPackage(method, exposeMethod));
    }

    // classRef is the reference to class use it to call methods
    public String build() {
        StringBuilder sb = new StringBuilder();
        // package declaration
        sb.append("package com.make73.annotation_processor;\n\n");
        // import statements
        sb.append("import org.springframework.web.bind.annotation.GetMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\n\n");

        sb.append("import com.make73.annotation_processor.lld_drivers.*;\n\n");


        // declare Controller class
        sb.append("@RestController()\n")
                .append("@RequestMapping(\"").append(className).append("\")\n")
                .append("public class ").append(controllerClassName).append(" {\n\n");

        // declare and initialize classRef
        sb.append("    private ").append(className).append(" classRef = new ").append(className).append("();\n\n");
//        sb.append(methods.size());
        // time to add methods
        for (MethodPackage methodPackage : methods) {
            ExecutableElement method = methodPackage.getExecutableElement();
            ExposeMethod exposeMethod = methodPackage.getExposeMethod();
            sb.append("    @GetMapping(\"/").append(exposeMethod.link()).append("\")\n");
            if (method.getReturnType().toString().equals("void")) {
                sb.append("    public String ").append(method.getSimpleName()).append("(");
            } else {
                sb.append("    public ").append(method.getReturnType()).append(" ").append(method.getSimpleName()).append("(");
            }
            List<? extends VariableElement> parameters = method.getParameters();
            for (int i = 0; i < parameters.size(); i++) {
                VariableElement param = parameters.get(i);
                sb.append(param.asType()).append(" arg").append(i);
                if (i < parameters.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("){\n");
            // inside method call the classRef.methodName with required arguments
            if (method.getReturnType().toString().equals("void")) {
                sb.append("\t\tclassRef.").append(method.getSimpleName()).append("(");
                for (int i = 0; i < parameters.size(); i++) {
                    sb.append("arg").append(i);
                    if (i < parameters.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(");\n");
                sb.append("\t\treturn \"request completed successfully\";\n");
            } else {
                sb.append("\t\treturn classRef.").append(method.getSimpleName()).append("(");
                for (int i = 0; i < parameters.size(); i++) {
                    sb.append("arg").append(i);
                    if (i < parameters.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(");\n");
            }
            sb.append("    }\n\n");
        }

        sb.append("}\n\n");
        return sb.toString();
    }

}
