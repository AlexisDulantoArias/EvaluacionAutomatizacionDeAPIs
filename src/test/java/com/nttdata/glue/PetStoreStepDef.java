package com.nttdata.glue;

import com.nttdata.steps.PetStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreStepDef {

    @Steps
    PetStoreStep orden;

    @Given("la URL base del servicio es {string}")
    public void laURLBaseDelServicioEs(String url) {
        orden.definirURL(url);
    }

    @When("creo un pedido con ID {int} para la mascota de ID {int} de cantidad {int}")
    public void creoUnPedidoConIDParaLaMascotaDeIDDeCantidad(int idPedido, int idMascota, int cantidadPedido) {
        orden.crearPedido(idPedido, idMascota, cantidadPedido);
    }

    @Then("valido que el código de respuesta sea {int}")
    public void validoQueElCodigoDeRespuestaSea(int statusCode) {
        orden.validarRespuesta(statusCode);
    }

    @And("verifico que el estado del pedido sea {string}")
    public void verificoQueElEstadoDelPedidoSea(String ordenEstado) {
        orden.validarEstadoDelPedido(ordenEstado);
    }

    @And("imprimo la respuesta del API")
    public void imprimoLaRespuestaDelAPI() {
        orden.imprimoRespuesta();
    }

    @When("consulto el ID del pedido {int}")
    public void consultoElIDDelPedido(int idPedido) {
        orden.consultoPedidoPorID(idPedido);
    }

    @And("verifico el id de la mascota {int}")
    public void verificoElIdDeLaMascota(int idMascota) {
        orden.verificoIdDeLaMascota(idMascota);
    }

    @And("verifico la cantidad del pedido {int}")
    public void verificoLaCantidadDelPedido(int cantidadPedido) {
        orden.verificoCandidadPedido(cantidadPedido);
    }

    @And("la respuesta debe tener todos los datos de la orden")
    public void laRespuestaDebeTenerTodosLosDatosDeLaOrden() {
        orden.imprimoRespuesta();
    }


}
