/**
 * Copyright (c) 2016, AskLytics and/or its affiliates. All rights reserved.
 * <p>
 * ASKLYTICS PROPRIETARY/CONFIDENTIAL. Use is subject to Non-Disclosure Agreement.
 * <p>
 * Created by bilalshah on 22/12/2016
 */
package com.api.versioning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
    int[] value();
}
