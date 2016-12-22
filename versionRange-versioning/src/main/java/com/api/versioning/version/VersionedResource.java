package com.api.versioning.version;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RequestMapping // Required as otherwise Spring won't combine the configuration if the class or method is not already
// annotated with RequestMapping
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VersionedResource {

    String from() default "";

    String to() default Version.MAX_VERSION;
}
