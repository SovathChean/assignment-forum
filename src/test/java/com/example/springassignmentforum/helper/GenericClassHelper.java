package com.example.springassignmentforum.helper;

import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

public class GenericClassHelper<T>
{
    public  ParameterizedTypeReference<T> getParameterizedTypeRef(Class<T> genericClass, Class<T> tClass)
    {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(genericClass, tClass);

        return  ParameterizedTypeReference.forType(resolvableType.getType());
    }
}
