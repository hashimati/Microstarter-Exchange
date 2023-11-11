package io.hashimati.domains;


import io.hashimati.domains.enums.MethodType;
import io.hashimati.domains.enums.ResponseAction;
import io.micronaut.data.annotation.*;
//import io.micronaut.http.MediaType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;

import static io.micronaut.data.model.naming.NamingStrategies.Raw;


@MappedEntity(value = "endpoints", namingStrategy = Raw.class)
@Schema(name = "Endpoint", description = "Endpoint Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Endpoint {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private String id;
    private String service;
    private String url;
    private String description;
    private MethodType type;

    private List<Parameter> parameters;
    private List<Parameter> pathVariables;
    private List<Header> headers;

    //produces
    private String contentType;

   //consumes
    private String mediaType;

    private ResponseAction responseAction;

    // in case responseAction is to SEND_TO_SHARED_LOCATION;
    private String sharedLocation;
    private String filePrefix;
    private String fileExtension;

    // in case responseAction is to SEND_TO_SOCKET;
    private String socketHost;
    private int socketPort;
    private String socketProtocol;


    @DateCreated
    private Date dateCreated;
    @DateUpdated
    private Date dateUpdated;

}

