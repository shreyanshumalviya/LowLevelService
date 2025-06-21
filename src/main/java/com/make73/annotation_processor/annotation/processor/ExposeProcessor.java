package com.make73.annotation_processor.annotation.processor;

import com.make73.annotation_processor.ClassBuilder;
import com.make73.annotation_processor.ParentController;
import com.make73.annotation_processor.annotation.ExposeMethod;
import com.make73.annotation_processor.dto.ControllerInfoDTO;
import com.make73.annotation_processor.dto.MethodInfoDTO;
import com.make73.annotation_processor.dto.ParameterInfoDTO;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@javax.annotation.processing.SupportedAnnotationTypes("com.make73.annotation_processor.annotation.ExposeClass")
@javax.annotation.processing.SupportedSourceVersion(javax.lang.model.SourceVersion.RELEASE_8)
public class ExposeProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // Initialization code, if needed
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Filer filer = processingEnv.getFiler();
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Processing: ");

        List<ControllerInfoDTO> controllers = new ArrayList<>();

        for (TypeElement annotation : annotations) {
            // Find elements annotated with MyCustomAnnotation
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                // Process each element
                try {
                    JavaFileObject fileObject = filer.createSourceFile("com.make73.annotation_processor." + element.getSimpleName().toString() + "Controller");
                    try (Writer writer = fileObject.openWriter()) {
                        // use ClassBuilder
                        ClassBuilder cb = new ClassBuilder();
                        ControllerInfoDTO controller = new ControllerInfoDTO();
                        controller.setName("Main");
                        controller.setLink("Main");
                        controller.setYear("2024");
                        controller.setStack("Java");
                        List<MethodInfoDTO> methods = new ArrayList<>();
                        controller.setMethods(methods);

                        controllers.add(controller);
                        cb.setClassName(element.getSimpleName().toString());
                        // add all methods
                        for (Element method : element.getEnclosedElements()) {
                            // if method has expose annotation
                            if (method.getAnnotation(ExposeMethod.class) != null) {
                                // get Method from Element
                                MethodInfoDTO methodInfoDTO = new MethodInfoDTO();
                                methodInfoDTO.setName(method.getSimpleName().toString());
                                methodInfoDTO.setLink("http://localhost:8080/Main/" + method.getSimpleName().toString());

                                List<ParameterInfoDTO> parameters = new ArrayList<>();

                                methodInfoDTO.setParameters(parameters);
                                methods.add(methodInfoDTO);
                                try {
                                    // Check if element is a method before casting
                                    if (method.getKind() == ElementKind.METHOD) {
                                        ExecutableElement methodElement = (ExecutableElement) method;
                                        int i = 0;
                                        for (Element param : methodElement.getParameters()) {
                                            ParameterInfoDTO parameterInfoDTO = new ParameterInfoDTO();
                                            parameterInfoDTO.setName(param.getSimpleName().toString());
                                            parameterInfoDTO.setKey("arg" + i);
                                            parameterInfoDTO.setType(param.asType().toString());
                                            parameters.add(parameterInfoDTO);
                                            i++;
                                        }
                                        cb.addMethod(methodElement);
                                    }
                                } catch (Exception e) {
//                                    writer.write(e.getMessage()+"\n");
                                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to generate class: " + e.getMessage());
                                }
                            }
                        }

                        writer.write(cb.build());
                    }
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to generate class: " + e.getMessage());
                }
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing: " + element.getSimpleName());

                // Your processing logic here
            }
            ParentController.setControllers(controllers);

        }
        return true; // No further processing of this annotation type
    }
}
