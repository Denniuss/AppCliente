package com.newmantech.appcliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DireccionService {

    @GET("venta/direccionDelivery/listar/direccion/3339")
    Call<List<Direccion>> getListadoDireccionesXCliente();

    @GET("ubigeo/listarUbigeo/01/00/00/00/2")
    Call<List<Ubigeo>> getListadoDepartamento();

    @GET("ubigeo/listarUbigeo/01/{codigoDepartamento}/{codigoProvincia}/00/3")
    Call<List<Ubigeo>> getListadoProvincia(@Path("codigoDepartamento") String codigoDepartamento,
                                           @Path("codigoProvincia") String codigoProvincia);

    @GET("ubigeo/listarUbigeo/01/{codigoDepartamento}/{codigoProvincia}/{codigoDistrito}/4")
    Call<List<Ubigeo>> getListadoDistrito(@Path("codigoDepartamento") String codigoDepartamento,
                                          @Path("codigoProvincia") String codigoProvincia,
                                          @Path("codigoDistrito") String codigoDistrito);

    //@POST("pedido/actualizarEstadoPedido")
    //Call<Integer> finalizarPedido(@Body PedidoPost pedido);

    //@POST("pedido/actualizarEstadoPedido")
    //Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

}
