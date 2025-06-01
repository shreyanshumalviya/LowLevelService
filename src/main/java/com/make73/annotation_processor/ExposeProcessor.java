package com.make73.annotation_processor;

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
import java.util.Set;

@javax.annotation.processing.SupportedAnnotationTypes("com.make73.annotation_processor.ExposeClass")
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

        for (TypeElement annotation : annotations) {
            // Find elements annotated with MyCustomAnnotation
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                // Process each element
                try {
                    JavaFileObject fileObject = filer.createSourceFile("com.make73.annotation_processor."+element.getSimpleName().toString()+"Controller");
                    try (Writer writer = fileObject.openWriter()) {
                        // use ClassBuilder
                        ClassBuilder cb = new ClassBuilder();
                        cb.setClassName(element.getSimpleName().toString());
                        // add all methods
                        for (Element method : element.getEnclosedElements()) {
                            // if method has expose annotation
                            if (method.getAnnotation(ExposeMethod.class) != null) {
                                // get Method from Element

//                                writer.write(method.getSimpleName().toString()+"\n");
//                                writer.write(method.getKind().toString()+"\n");
                                try {
                                    // Check if element is a method before casting
                                    if (method.getKind() == ElementKind.METHOD) {
                                        ExecutableElement methodElement = (ExecutableElement) method;
                                        cb.addMethod(methodElement);
                                    }
                                }catch (Exception e){
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
        }
        return true; // No further processing of this annotation type
    }
}
