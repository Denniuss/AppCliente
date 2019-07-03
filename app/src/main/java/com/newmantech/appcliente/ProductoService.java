package com.newmantech.appcliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductoService {

    @GET("producto/listadoProducto")
    Call<List<CatalogoProducto>> getListadoCatalogoProducto();

    @POST("pedido/actualizarEstadoPedido")
    Call<Integer> finalizarPedido(@Body PedidoPost pedido);

    @POST("pedido/actualizarEstadoPedido")
    Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

}
