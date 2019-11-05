package com.newmantech.appcliente.service;

import com.newmantech.appcliente.model.CarritoDetalle;
import com.newmantech.appcliente.model.Pedido;
import com.newmantech.appcliente.model.WorkFlow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClienteService {

    @GET("pedido/listarPedidosPorIdCliente/{idCliente}")
    Call<List<Pedido>> listarPedidosPorIdCliente(@Path("idCliente") Integer idCliente);

    @GET("carritoDetalle/obtenerDetalleOrdenPorIdPedido/{idPedido}")
    Call<List<CarritoDetalle>> obtenerDetalleOrdenPorIdPedido(@Path("idPedido") Long idPedido);



}
