package io.hashimati.domains;

import io.micronaut.data.annotation.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Parameter{

            private String name;
            private String value;
            private String description;
            private String type;
            private String defaultValue;
            private boolean required;
            private boolean deprecated;
            private boolean allowEmptyValue;
            private boolean allowReserved;
            private boolean explode;

}
