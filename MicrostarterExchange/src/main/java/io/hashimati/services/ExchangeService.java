package io.hashimati.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.hashimati.domains.Endpoint;
import io.hashimati.domains.ServiceRequest;
import io.hashimati.domains.WebService;
import io.hashimati.domains.enums.MethodType;
import io.hashimati.util.RegexUtil;
import io.micronaut.http.BasicAuth;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Optional;

import static io.hashimati.domains.enums.SecurityType.BASIC;

@Singleton
public class ExchangeService {

    private WebServiceService webServiceService;

    private EndpointService endpointService;

    private GeneralWebClient generalWebClient;

    public ExchangeService(WebServiceService webServiceService, EndpointService endpointService, GeneralWebClient generalWebClient) {
        this.webServiceService = webServiceService;
        this.endpointService = endpointService;
        this.generalWebClient = generalWebClient;
    }

    public HttpResponse exchange(ServiceRequest serviceRequest) throws JsonProcessingException {
        Optional<WebService> WebService = webServiceService.findByAlias(serviceRequest.getServiceName(), null,     null);


        if(!WebService.isPresent()) {
            System.out.println("Web service not found");
            return HttpResponse.notFound();
        }

        var webservice = WebService.get();

        System.out.println(webservice.getName());
        Optional<Endpoint> EndPoint = endpointService.findByWebServiceAndUrl(webservice.getAlias(), serviceRequest.getEndpoint(), null, null);

        if(!EndPoint.isPresent()) return HttpResponse.notFound();

        var endpoint = EndPoint.get();

        BasicAuth basicAuth = null;

        if(webservice.getSecurityType() == BASIC)
        {
            if(serviceRequest.getUsername() != null && !serviceRequest.getUsername().isBlank() && serviceRequest.getPassword() != null && !serviceRequest.getPassword().isBlank())
            {
                basicAuth = new BasicAuth(serviceRequest.getUsername(), serviceRequest.getPassword());
            }
            else
            {
                return HttpResponse.badRequest(new HashMap<String, String>(){{
                    put("message", "Please, provide the credentials");
                }});
            }
        }

        System.out.println(webservice.getRootUrl());
        System.out.println(endpoint.getUrl());
        String endpointUrl = webservice.getRootUrl() + endpoint.getUrl();;
        var pathVariable = RegexUtil.getPathVariables(endpointUrl);

        if(pathVariable != null && !pathVariable.isEmpty())
        {
            for(String key: pathVariable)
            {
                if(!serviceRequest.getPathVariables().containsKey(key)) {
                    HttpResponse.badRequest(new HashMap<String, String>() {{
                        put("message", "Please, provide the path variable: " + key);
                    }});
                }
                endpointUrl = endpointUrl.replace("{" + key + "}", serviceRequest.getPathVariables().get(key));
            }
        }

        switch (endpoint.getType()) {
            case GET:

                System.out.println(endpoint.getContentType());

                System.out.println(MediaType.isTextBased(endpoint.getContentType()));
                if(MediaType.isTextBased(endpoint.getContentType())) {

                     System.out.println("I'm here");

                    return HttpResponse.ok(generalWebClient.getTextBased(endpointUrl, serviceRequest.getParameters(), serviceRequest.getHeaders(), basicAuth));
                }
                else
                  return HttpResponse.ok(generalWebClient.getBinaryBased(endpointUrl, serviceRequest.getParameters(), serviceRequest.getHeaders(), basicAuth));


            case POST:

                if(MediaType.isTextBased(endpoint.getContentType()))
                    return HttpResponse.ok(generalWebClient.postTextBased(endpointUrl,serviceRequest.getBody(),serviceRequest.getHeaders(), basicAuth));
                else
                    return HttpResponse.ok(generalWebClient.postBinaryBased(endpointUrl,serviceRequest.getBody(),serviceRequest.getHeaders(), basicAuth));

                case PUT:
                    if(MediaType.isTextBased(endpoint.getContentType()))
                        return HttpResponse.ok(generalWebClient.putTextBased(endpointUrl,serviceRequest.getBody(),serviceRequest.getHeaders(), basicAuth));
                    else
                        return HttpResponse.ok(generalWebClient.putBinaryBased(endpointUrl,serviceRequest.getBody(),serviceRequest.getHeaders(), basicAuth));

//                return generalWebClient.put(endpoint.getUrl(), serviceRequest.getHeaders(), serviceRequest.getQueryParams(), serviceRequest.getBody());
            case DELETE:
//                return generalWebClient.delete(endpoint.getUrl(), serviceRequest.getHeaders(), serviceRequest.getQueryParams());
            default:
                return HttpResponse.notFound();
        }


    }
}
