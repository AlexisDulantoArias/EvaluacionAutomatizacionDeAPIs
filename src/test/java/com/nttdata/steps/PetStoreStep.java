package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;


public class PetStoreStep {

    Response response;
    private String URL_BASE;

    public void definirURL(String url) {
        URL_BASE = url;
    }

    public void crearPedido(int idPedido, int idMascota, int cantidadPedido) {
        String body = "{\n" +
                "  \"id\": " + idPedido + ",\n" +
                "  \"petId\": \"" + idMascota + "\",\n" +
                "  \"quantity\": \"" + cantidadPedido + "\",\n" +
                "  \"shipDate\": \"2024-12-17T20:08:36.494Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": \"true\"\n" +
                "}";


        response = RestAssured
                .given()
                .baseUri(URL_BASE)
                .header("Content-Type", "application/json")
                .relaxedHTTPSValidation()
                .body(body)
                .log().all()
                .post("/store/order")
                .then()
                .extract().response()
        ;
    }

    public void validarRespuesta(int statusCode) {
        Assert.assertEquals("Validación de respuesta", statusCode, response.statusCode());
    }

    public void validarEstadoDelPedido(String ordenEstado) {
        Assert.assertEquals("Estado del pedido: ", ordenEstado, response.jsonPath().getString("status"));
    }

    public void imprimoRespuesta() {
        System.out.println(response.print());
    }

    public void consultoPedidoPorID(int idPedido) {
        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .log().all()
                .get("/store/order/" + idPedido)
                .then()
                .log().all()
                .extract().response();
        ;
    }

    public void verificoIdDeLaMascota(int idMascota) {
        int quantityFromResponse = Integer.parseInt(response.jsonPath().getString("petId"));
        Assert.assertEquals("ID de la mascota: ", idMascota, quantityFromResponse);
    }

    public void verificoCandidadPedido(int cantidadPedido) {
        int quantityFromResponse = Integer.parseInt(response.jsonPath().getString("quantity"));
        Assert.assertEquals("Cantidad del Pedido: ", cantidadPedido, quantityFromResponse);
    }
}
