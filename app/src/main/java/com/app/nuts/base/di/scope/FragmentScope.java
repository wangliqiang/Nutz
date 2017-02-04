package com.app.nuts.base.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 王立强 on 2017/2/4.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {}

