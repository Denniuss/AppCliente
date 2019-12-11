package com.newmantech.appcliente.service;

import com.newmantech.appcliente.model.BResult;
import com.newmantech.appcliente.model.EntityDetalleCarritoPersonalizado;
import com.newmantech.appcliente.model.PedidoPost;
import com.newmantech.appcliente.model.CarritoCompra;
import com.newmantech.appcliente.model.CatalogoProducto;
import com.newmantech.appcliente.model.WorkFlow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("producto/listadoProducto")
    Call<List<CatalogoProducto>> getListadoCatalogoProducto();

    /*@Headers({
            "Accept: application/json",
            "User-Agent: DemoApp",
            "Cache-Control: max-age=640000"
    })*/
    @Headers( {
            "Cache-Control: max-age=3600"
    })
    @GET("carritoProducto/agregarItem/{keyItemCanje}/{cantidad}")
    Call<BResult> agregarCarritoCompras(@Path("keyItemCanje")String keyItemCanje,
                                        @Path("cantidad")Integer cantidad);

    @GET("carritoProducto/quitar")
    Call<BResult> eliminarCarritoCompras(@Query("codigo") Integer codigo);

    @GET("carritoProducto/listar")
    Call<CarritoCompra> listarCarritoCompras();

    @GET("catalogo/detallePorCodigoNetsuite/{codigoNetsuite}")
    Call<CatalogoProducto> detalleProducto(@Path("codigoNetsuite") String codigoNetsuite);

    @GET("canjeProducto/finalizar/{codigoOperacionPaypal}")
    Call<BResult> finalizarCompraPaypal(@Path("codigoOperacionPaypal")String codigoOperacionPaypal);


    @GET("carritoProducto/quitar?codigo=43122")
    Call<List<CatalogoProducto>> quitarCarritoCompras();

    @POST("pedido/actualizarEstadoPedido")
    Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

    @POST("pedido/actualizarEstadoPedido")
    Call<Integer> finalizarPedido(@Body PedidoPost pedido);

    @GET("pedido/obtenerFlujoPorIdPedido/{idPedido}")
    Call<WorkFlow> obtenerEstadoPorIdPedido(@Path("idPedido") Long idPedido);


    @Headers( {
            "Cache-Control: max-age=3600"
    })
    @GET("carritoProducto/agregarCarritoPersonalizado/{idDetalleTarifario}/{idProducto}/{cantidad}")
    Call<BResult> agregarCarritoComprasPersonalizado(@Path("idDetalleTarifario") Integer idDetalleTarifario,
                                                     @Path("idProducto") Integer idProducto,
                                                     @Path("cantidad") Integer cantidad);

    @GET("carritoProducto/obtenerCarritoCompraPersonalizado")
    Call<List<EntityDetalleCarritoPersonalizado>> obtenerCarritoCompraPersonalizado();

}
