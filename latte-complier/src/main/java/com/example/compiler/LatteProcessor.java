package com.example.compiler;

import com.example.annoations.AppRegisterGenerator;
import com.example.annoations.EntryGenerator;
import com.example.annoations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by WeLot on 2018/4/17.
 */

@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {


    //
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnoations = getsupportedAnnoatations();
        for (Class<? extends Annotation> annoation : supportAnnoations) {
            types.add(annoation.getCanonicalName());
        }

        return types;
    }

    //
    private Set<Class<? extends Annotation>> getsupportedAnnoatations() {
        final Set<Class<? extends Annotation>> annoations = new LinkedHashSet<>();
        annoations.add(EntryGenerator.class);
        annoations.add(PayEntryGenerator.class);
        annoations.add(AppRegisterGenerator.class);
        return annoations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterEntryCode(roundEnvironment);
        return true;
    }

    /**
     * @param env
     * @param annoation
     * @param visitor
     */
    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annoation,
                      AnnotationValueVisitor visitor) {
        //
        for (Element typeElement : env.getElementsAnnotatedWith(annoation)) {
            List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            //
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                /**
                 *
                 *
                 */
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValue =
                        annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValue.entrySet()) {

                    entry.getValue().accept(visitor, null);
                }

            }
        }

    }

    /**
     *
     */
    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setmFiler(processingEnv.getFiler());
        //
        scan(env,EntryGenerator.class,entryVisitor);

    }

    /**
     *
     */
    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor entryVisitor = new PayEntryVisitor();
        entryVisitor.setmFiler(processingEnv.getFiler());
        //
        scan(env,PayEntryGenerator.class,entryVisitor);

    }


    /**
     *
     */
    private void generateAppRegisterEntryCode(RoundEnvironment env) {
        final AppRegisterEntryVisitor entryVisitor = new AppRegisterEntryVisitor();
        entryVisitor.setmFiler(processingEnv.getFiler());
        //
        scan(env,AppRegisterGenerator.class,entryVisitor);

    }
}
