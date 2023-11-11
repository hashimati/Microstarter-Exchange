package io.hashimati.domains;



import io.micronaut.data.annotation.*;
import lombok.*;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import static io.micronaut.data.model.naming.NamingStrategies.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@MappedEntity(value = "scheduledtasks", namingStrategy = Raw.class)
@Schema(name="ScheduledTask", description="ScheduledTask Description")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ScheduledTask{
    @Id 
    @GeneratedValue(GeneratedValue.Type.AUTO) 
    private String id;

    private String name;
	private String service;
	private String endpoint;
	private String frequency;
    private String description;
    private String cron;
    private String timezone;
    private String status;
    private String lastRun;
    private String nextRun;
    @DateCreated private Date dateCreated;
    @DateUpdated private Date dateUpdated;


}

