package io.hashimati.domains;


import io.hashimati.domains.enums.BodyType;
import io.hashimati.domains.enums.MethodType;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ServiceRequest {

    private String serviceName;
    private String endpoint;
    private List<Header> headers;
    private List<Parameter> parameters;
    private HashMap<String, Object> body;
    private BodyType bodyType;
    private HashMap<String, String> pathVariables;
    private MethodType methodType;
    private String username;
    private String password;


}
