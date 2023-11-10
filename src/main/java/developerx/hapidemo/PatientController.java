package developerx.hapidemo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.util.BundleBuilder;
import developerx.swagger.PatientDocumentation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.r5.model.*;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Collections;

@RestController
@RequestMapping(path = "patients")
public class PatientController {
    private final FhirContext fhirContext;

    public PatientController(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @PostMapping
    public String createPatient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = PatientDocumentation.DESCRIPTION,
                    content = @Content(
                            examples = {
                                @ExampleObject(
                                        value = PatientDocumentation.EXAMPLE
                                )
                            }
                    )
            )
            @RequestBody Patient patient
    ) {
        return "ok";
    }

    @GetMapping
    public @Schema(implementation = Bundle.class) Bundle getPatients() {
        BundleBuilder builder = new BundleBuilder(fhirContext);
        IBase entry = builder.addEntry();

        Identifier identifier = new Identifier()
                .setValue("0000001");
        Address address = new Address()
                .setText("123 Main Street")
                .setCity("Any City")
                .setPostalCode("123456")
                .setCountry("USA");
        HumanName name = new HumanName()
                .setGiven(Collections.singletonList(new StringType("John")))
                .setFamily("Doe");
        Patient patient = new Patient()
                .setIdentifier(Collections.singletonList(identifier))
                .setAddress(Collections.singletonList(address))
                .setName(Collections.singletonList(name));

        builder.addToEntry(entry, "resource", patient);
        return (Bundle)builder.getBundle();
    }

    @GetMapping("{id}")
    public Patient getPatientById(@PathVariable long id) {
        Identifier identifier = new Identifier()
                .setValue(new DecimalFormat("0000000").format(id));
        Address address = new Address()
                .setText("123 Main Street")
                .setCity("Any City")
                .setPostalCode("123456")
                .setCountry("USA");
        HumanName name = new HumanName()
                .setGiven(Collections.singletonList(new StringType("John")))
                .setFamily("Doe");
        return new Patient()
                .setIdentifier(Collections.singletonList(identifier))
                .setAddress(Collections.singletonList(address))
                .setName(Collections.singletonList(name));
    }

}
