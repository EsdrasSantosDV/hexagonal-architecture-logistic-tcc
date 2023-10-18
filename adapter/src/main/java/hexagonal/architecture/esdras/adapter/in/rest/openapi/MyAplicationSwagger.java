package hexagonal.architecture.esdras.adapter.in.rest.openapi;


import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Hexagonal Architecture TCC",
                version = "1.0.0",
                contact = @Contact(
                        name = "Esdras Santos de Oliveira",
                        url = "https://github.com/EsdrasSantosDV/hexagonal-architecture-logistic-tcc",
                        email = "esdras.oliveira@estudante.iftm.edu.br"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"))
)
public class MyAplicationSwagger extends Application {
}


