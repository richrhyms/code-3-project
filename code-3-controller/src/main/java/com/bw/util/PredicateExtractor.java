package com.bw.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Named;
import javax.inject.Provider;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class PredicateExtractor {

    private final EntityPathResolver entityPathResolver;
    private final QuerydslPredicateBuilder predicateBuilder;
    private final Provider<WebRequest> webRequest;

    public PredicateExtractor(
            QuerydslBindingsFactory bindingsFactory,
            Optional<ConversionService> conversionService,
            Provider<WebRequest> webRequest) {

        this.entityPathResolver = bindingsFactory.getEntityPathResolver();
        this.predicateBuilder = new QuerydslPredicateBuilder(conversionService.orElseGet(DefaultConversionService::new),
                entityPathResolver);
        this.webRequest = webRequest;
    }

//    public <E, Q extends EntityPath<E>> void applyPredicate(
//            JPAQuery<E> jpaQuery,
//            QuerydslBinderCustomizer<Q> binderCustomizer,
//            MultiValueMap<String, String> parameters) {
//        getPredicate(binderCustomizer, Optional.ofNullable(parameters)).ifPresent(jpaQuery::where);
//    }

//    public <E, Q extends EntityPath<E>> Optional<Predicate> getPredicate(
//            QuerydslBinderCustomizer<Q> binderCustomizer,
//            Optional<MultiValueMap<String, String>> parameters) {
//        return parameters.map(params -> getPredicate(binderCustomizer, params));
//    }

    private static <E, Q extends EntityPath<E>> Class<E> getEntityType(QuerydslBinderCustomizer<Q> binderCustomizer) {
        ParameterizedType bType = Arrays.asList(binderCustomizer.getClass().getGenericInterfaces())
                .stream()
                .filter(it -> it instanceof ParameterizedType)
                .map(it -> (ParameterizedType) it)
                .peek(it -> it.getRawType().getTypeName().equals(QuerydslBinderCustomizer.class.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        Class<Q> qType = (Class<Q>) bType.getActualTypeArguments()[0];
        return (Class<E>) ((ParameterizedType) qType.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public <E, Q extends EntityPath<E>> Predicate getPredicate(
            QuerydslBinderCustomizer<Q> binderCustomizer) {
        return getPredicate(binderCustomizer, getParameterMap());
    }

    public <E, Q extends EntityPath<E>> Predicate getPredicate(
            QuerydslBinderCustomizer<Q> binderCustomizer,
            MultiValueMap<String, String> parameters) {
        Class<E> entityType = getEntityType(binderCustomizer);
        Q entityPath = (Q) entityPathResolver.createPath(entityType);
        QuerydslBindings bindings = new QuerydslBindings();
        binderCustomizer.customize(bindings, entityPath);
        return predicateBuilder.getPredicate(ClassTypeInformation.from(entityType), parameters, bindings);
    }

    public MultiValueMap<String, String> getParameterMap() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String[]> entry : webRequest.get().getParameterMap().entrySet()) {
            parameters.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        return parameters;
    }
}
