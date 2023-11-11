package io.hashimati.domains;


import io.hashimati.domains.enums.SecurityCharset;
import io.hashimati.domains.enums.SecurityType;
import io.hashimati.domains.enums.ServiceType;
import io.hashimati.domains.enums.WSSTatus;
import io.micronaut.data.annotation.*;
import io.micronaut.http.BasicAuth;
import lombok.*;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import static io.micronaut.data.model.naming.NamingStrategies.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@MappedEntity(value = "webservices", namingStrategy = Raw.class)
@Schema(name="WebService", description="WebService Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class WebService{
    @Id 
    @GeneratedValue(GeneratedValue.Type.AUTO) 
    private String id;
    private ServiceType type;

    private String name;
    private String alias;

	private String description;
	private String provider;
	private String rootUrl;
	private String documentation;
    private SecurityType securityType;
    private SecurityCharset securityCharset;

    private WSSTatus status;

    private String username;
    private String password;
    private String loginUrl;
    private String refreshTokenUrl;
    private String token;
    private String tokenType;
    private String refreshToken;
    private String clientId;
    private String clientSecret;
    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;


}

