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

public final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    //
    private Filer mFiler;
    //
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
     */
    //type
    @Override
    public Void visitType(TypeMirror typeMirror, Void p) {
        mTypeMirror = typeMirror;
        generateJavaCode();
        return p;

    }

    /**
     */
    private void generateJavaCode() {
        //
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXPayEntryActivity")//
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))//
                .build();

        final JavaFile  javaFile   =  JavaFile.builder(mPackageName+".wxapi",targetActivity)
                .addFileComment("微信支付入口文件")//
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
