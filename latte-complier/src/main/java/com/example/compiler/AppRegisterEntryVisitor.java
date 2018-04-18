package com.example.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by WeLot on 2018/4/17.
 */

public final class AppRegisterEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    //需要遍历
    private Filer mFiler;
    //类型
    private TypeMirror mTypeMirror;
    private String mPackageName;


    public void setmFiler(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    /**
     * 找出注解修饰类 并找出注解的元信息，生成代码
     */
    //type 类类型
    @Override
    public Void visitType(TypeMirror typeMirror, Void p) {
        mTypeMirror = typeMirror;
        generateJavaCode();
        return p;

    }

    /**
     * 生成，模板代码
     */
    private void generateJavaCode() {
        //生成需要的类
        final TypeSpec targetActivity = TypeSpec.classBuilder("AppRegister")//传入类名
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))//继承自从注解中拿出来的类类型
                .build();
        //生成文件                                    //包名
        final JavaFile  javaFile   =  JavaFile.builder(mPackageName+".wxapi",targetActivity)
                .addFileComment("微信广播接收器")//注释
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
