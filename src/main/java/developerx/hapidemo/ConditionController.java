package developerx.hapidemo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.util.BundleBuilder;
import developerx.swagger.ConditionDocumentation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.r5.model.*;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Collections;

@RestController
@RequestMapping(path = "conditions")
public class ConditionController {

    private final FhirContext fhirContext;

    public ConditionController(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @PostMapping
    public String createCondition(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = ConditionDocumentation.DESCRIPTION,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            value = ConditionDocumentation.EXAMPLE
                                    )
                            }
                    )
            )
            @RequestBody Condition condition
    ) {
        return "ok";
    }

    @GetMapping
    public @Schema(implementation = Bundle.class) Bundle getConditions() {
        BundleBuilder builder = new BundleBuilder(fhirContext);
        IBase entry = builder.addEntry();

        Identifier identifier = new Identifier()
                .setValue("0000001");
        Coding coding = new Coding()
                .setCode("39065001")
                .setSystem("http://snomed.info/sct")
                .setDisplay("Burn of ear");
        CodeableConcept codeableConcept = new CodeableConcept()
                .setCoding(Collections.singletonList(coding))
                .setText("Burnt Ear");
        Condition condition = new Condition()
                .setIdentifier(Collections.singletonList(identifier))
                .setCode(codeableConcept);

        builder.addToEntry(entry, "resource", condition);
        return (Bundle)builder.getBundle();
    }

    @GetMapping("{id}")
    public Condition getConditionById(@PathVariable long id) {
        Identifier identifier = new Identifier()
                .setValue(new DecimalFormat("0000000").format(id));
        Coding coding = new Coding()
                .setCode("39065001")
                .setSystem("http://snomed.info/sct")
                .setDisplay("Burn of ear");
        CodeableConcept codeableConcept = new CodeableConcept()
                .setCoding(Collections.singletonList(coding))
                .setText("Burnt Ear");
        return new Condition()
                .setIdentifier(Collections.singletonList(identifier))
                .setCode(codeableConcept);
    }
}
