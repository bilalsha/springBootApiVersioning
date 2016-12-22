package com.api.versioning;

import com.api.versioning.version.VersionRange;
import com.api.versioning.version.VersionedResource;
import com.api.versioning.version.VersionedResourceRequestCondition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        VersionedResource typeAnnotation = AnnotationUtils.findAnnotation(handlerType, VersionedResource.class);
        return createCondition(typeAnnotation);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
         VersionedResource methodAnnotation = AnnotationUtils.findAnnotation(method, VersionedResource.class);
        return createCondition(methodAnnotation);
    }

    private RequestCondition<?> createCondition(VersionedResource versionMapping) {
        if (versionMapping != null) {
            return new VersionedResourceRequestCondition(versionMapping.from(), versionMapping.to());
        }

        return null;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

        VersionedResource methodAnnotation = AnnotationUtils.findAnnotation(method, VersionedResource.class);
        if(methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            // Concatenate our ApiVersion with the usual request mapping
            info = createApiVersionInfo(methodAnnotation, methodCondition).combine(info);
        } else {
            VersionedResource typeAnnotation = AnnotationUtils.findAnnotation(handlerType, VersionedResource.class);
            if(typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                // Concatenate our ApiVersion with the usual request mapping
                info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
            }
        }

        return info;
    }

    private RequestMappingInfo createApiVersionInfo(VersionedResource annotation, RequestCondition<?> customCondition) {
        String[] patterns = new String[2];
        VersionRange versionRange = new VersionRange(annotation.from(), annotation.to());
        for (int i = 0; i<1; i++){
            patterns[i] = annotation.from();
        }


        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(), useTrailingSlashMatch(), getFileExtensions()),
                new RequestMethodsRequestCondition(),
                new ParamsRequestCondition(),
                new HeadersRequestCondition(),
                new ConsumesRequestCondition(),
                new ProducesRequestCondition(),
                customCondition);
    }

}