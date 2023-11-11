package io.hashimati.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.hashimati.domains.Endpoint;
import io.hashimati.domains.Parameter;
import io.hashimati.domains.ServiceRequest;
import io.hashimati.domains.WebService;
import io.hashimati.domains.enums.MethodType;
import io.hashimati.services.EndpointService;
import io.hashimati.services.ExchangeService;
import io.hashimati.services.WebServiceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Arrays;

@Controller("/exchange")
public class ExchangeController {


    private ExchangeService exchangeService;

    @Inject
    private WebServiceService webServiceService;

    @Inject
    private EndpointService endpointService;
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Post("/consume")
    public HttpResponse exchange(@Body ServiceRequest serviceRequest) throws JsonProcessingException {
        return exchangeService.exchange(serviceRequest);
    }

    @Get("/create")
    public String createWebservice()
    {
        WebService service = new WebService();
        service.setName("test");
        service.setRootUrl("http://localhost:8787/exchange");

        webServiceService.save(service, null, null);

        Endpoint endpoint = new Endpoint();
        endpoint.setUrl("test");
        endpoint.setType(MethodType.GET);

        endpoint.setService(service.getName());
        Parameter parameter = new Parameter();
        parameter.setName("name");
        parameter.setDescription("name");
        parameter.setType("String");
        endpoint.setParameters(Arrays.asList(parameter));
        endpointService.save(endpoint, null, null);
        return "Done";
    }
    @Get("/test")
    public String hi(String name)
    {

        return "Hello " + name;
    }
    @Get("/testConsume")
    public HttpResponse<?> testConsume() throws JsonProcessingException {
        ServiceRequest request = new ServiceRequest();
        request.setEndpoint("/test");
        request.setServiceName("test");
        Parameter parameter = new Parameter();

        parameter.setName("name");
        parameter.setValue("hashimati");

        request.setParameters(Arrays.asList(parameter));
        return exchange(request);
    }

}
