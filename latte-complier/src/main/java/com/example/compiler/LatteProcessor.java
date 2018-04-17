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


    //获取注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnoations = getsupportedAnnoatations();
        for (Class<? extends Annotation> annoation : supportAnnoations) {
            types.add(annoation.getCanonicalName());
        }

        return types;
    }

    //获取被注解修饰的类的类型
    private Set<Class<? extends Annotation>> getsupportedAnnoatations() {
        final Set<Class<? extends Annotation>> annoations = new LinkedHashSet<>();
        annoations.add(EntryGenerator.class);
        annoations.add(PayEntryGenerator.class);
        annoations.add(AppRegisterGenerator.class);
        return annoations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        return false;
    }

    /**
     * @param env       系统运行的整个环境
     * @param annoation
     * @param visitor
     */
    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annoation,
                      AnnotationValueVisitor visitor) {
        //获取被注解修饰的元素
        for (Element typeElement : env.getElementsAnnotatedWith(annoation)) {
            List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            //镜像
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                /**
                 * 键：可执行的element
                 * 值：注解的值
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
     * 生成微信entryActivity
     */
    private void generateEntryCode() {

    }
}
