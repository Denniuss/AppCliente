package com.newmantech.appcliente.service;

import com.newmantech.appcliente.model.BResult;
import com.newmantech.appcliente.model.Cliente;
import com.newmantech.appcliente.model.Direccion;
import com.newmantech.appcliente.model.Ubigeo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DireccionService {

    @GET("venta/direccionDelivery/listar/direccion/{idCliente}")
    Call<List<Direccion>> getListadoDireccionesXCliente(@Path("idCliente") Integer idCliente);

    @GET("ubigeo/listarUbigeo/01/00/00/00/2")
    Call<List<Ubigeo>> getListadoDepartamento();

    @GET("ubigeo/listarUbigeo/01/{codigoDepartamento}/{codigoProvincia}/00/3")
    Call<List<Ubigeo>> getListadoProvincia(@Path("codigoDepartamento") String codigoDepartamento,
                                           @Path("codigoProvincia") String codigoProvincia);

    @GET("ubigeo/listarUbigeo/01/{codigoDepartamento}/{codigoProvincia}/{codigoDistrito}/4")
    Call<List<Ubigeo>> getListadoDistrito(@Path("codigoDepartamento") String codigoDepartamento,
                                          @Path("codigoProvincia") String codigoProvincia,
                                          @Path("codigoDistrito") String codigoDistrito);

    @POST("cliente/direccionDelivery/actualizar")
    Call<BResult> registrarDireccion(@Body Cliente cliente);


    //@POST("pedido/actualizarEstadoPedido")
    //Call<Integer> finalizarPedido(@Body PedidoPost pedido);

    //@POST("pedido/actualizarEstadoPedido")
    //Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

}
