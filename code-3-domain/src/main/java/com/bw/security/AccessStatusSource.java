package com.bw.security;

import java.lang.annotation.Annotation;


public interface AccessStatusSource<A extends Annotation> {

    AccessStatus getStatus(A accessConstraint);
}
