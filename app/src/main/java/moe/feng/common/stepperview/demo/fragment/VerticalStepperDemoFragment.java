package moe.feng.common.stepperview.demo.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.adapter.CustomListAdapter;
import com.newmantech.appcliente.model.CarritoDetalle;
import com.newmantech.appcliente.service.ClienteService;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.WorkFlow;
import com.newmantech.appcliente.utils.ListItemPedidoSeguimiento;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import moe.feng.common.stepperview.VerticalStepperItemView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import moe.feng.common.stepperview.R;

public class VerticalStepperDemoFragment extends Fragment {

	private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[5];
	private Button mNextBtn0, mNextBtn1, mPrevBtn1, mNextBtn2, mPrevBtn2;
	private TextView idCreacion, idAbatecimiento,idDistribuciom,idFinalizacion,idIncidencia,idDetallPedido;
	private int mActivatedColorRes = R.color.material_blue_500;
	private int mDoneIconRes = R.drawable.ic_done_white_16dp;

	private ListView mListView;
	private ArrayAdapter aAdapter;

	Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlPis)
			.addConverterFactory(GsonConverterFactory.create())
			.build();

	ProductoService productoService = retrofit.create(ProductoService.class);


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_vertical_stepper, parent, false);
		/*idCreacion = (TextView) view.findViewById(R.id.idCreacion);
		idAbatecimiento = (TextView) view.findViewById(R.id.idAbastecimiento);
		idDistribuciom = (TextView) view.findViewById(R.id.idDistribucion);
		idIncidencia = (TextView) view.findViewById(R.id.idIncidencia);
		idFinalizacion = (TextView) view.findViewById(R.id.idFinalizar);*/

		return inflater.inflate(R.layout.fragment_vertical_stepper, parent, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		/*ArrayList userList = getListData();
		final ListView lv = (ListView) view.findViewById(R.id.user_list);
		lv.setAdapter(new CustomListAdapter(getContext(), userList));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 					ListItemPedidoSeguimiento user = (ListItemPedidoSeguimiento) lv.getItemAtPosition(position);
					Toast.makeText(getContext(), "Selected :" + " " + user.getId()+", "+ user.getNombre(), Toast.LENGTH_SHORT).show();
				}
			});*/


		Log.i("onResponse argumento", "curIdPedido: " + getArguments().getLong("curIdPedido"));
		Log.i("onResponse argumento", "curEstado: " + getArguments().getString("curEstado"));
		Log.i("onResponse argumento", "curCantidad: " + getArguments().getString("curCantidad"));
		Log.i("onResponse argumento", "curFechaPedido: " + getArguments().getString("curFechaPedido"));

		Long idPedido = getArguments().getLong("curIdPedido");
		getArguments().getString("curEstado");
		getArguments().getString("curCantidad");
		getArguments().getString("curFechaPedido");

		getListData(view,idPedido);

		idCreacion = (TextView) view.findViewById(R.id.idCreacion);
		idAbatecimiento = (TextView) view.findViewById(R.id.idAbastecimiento);
		idDistribuciom = (TextView) view.findViewById(R.id.idDistribucion);
		idIncidencia = (TextView) view.findViewById(R.id.idIncidencia);
		idFinalizacion = (TextView) view.findViewById(R.id.idFinalizar);
		//idDetallPedido = (TextView) view.findViewById(R.id.idDetallPedido);

		mSteppers[0] = view.findViewById(R.id.stepper_creacion);
		mSteppers[1] = view.findViewById(R.id.stepper_abastecimiento);
		mSteppers[2] = view.findViewById(R.id.stepper_distribucion);
		mSteppers[3] = view.findViewById(R.id.stepper_incidencia);
        mSteppers[4] = view.findViewById(R.id.stepper_finalizar);

		VerticalStepperItemView.bindSteppers(mSteppers);

		mSteppers[3].setVisibility(View.GONE);


		Call<WorkFlow> lista = productoService.obtenerEstadoPorIdPedido(idPedido);
		lista.enqueue(new Callback<WorkFlow>() {
			@Override
			public void onResponse(Call<WorkFlow> call, Response<WorkFlow> response) {
				Log.i("onResponse chamado", "onResponse: ");

				Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
				if(response.isSuccessful()) {

					Log.i("SIZE ", "onResponse: " + response.body());

					WorkFlow respuesta = response.body();

					Utilitario.ESTADO_FLUJO_TRABAJO estado = Utilitario.ESTADO_FLUJO_TRABAJO.getEstadoWorkFlowByID(respuesta.getEstado());
					/*Selecciona sl state por defecto*/
					switch (estado) {
						case CREACION_FLUJO:
						case APROBACION_FLUJO:
							idCreacion.setText(estado.getKeyMsg());
							mSteppers[0].setState(VerticalStepperItemView.STATE_SELECTED);
							break;
						case ABASTECIMIENTO_FLUJO:
						case PRODUCCION_FLUJO:
						case CONTROL_CALIDAD_FLUJO:
							idAbatecimiento.setText(estado.getKeyMsg());
							mSteppers[1].setState(VerticalStepperItemView.STATE_SELECTED);
							break;

						case PRE_DISTRIBUCION_FLUJO:
						case INCIO_ENTREGA_REPROGRAMAOD:
						case INCIO_ENTREGA_FLUJO:
							idDistribuciom.setText(estado.getKeyMsg());
							//view.id.marca.setText("Marca: " +items.get(i).getMarca());

							mSteppers[2].setState(VerticalStepperItemView.STATE_SELECTED);
							break;

						case INCIDENCIA_FLUJO:
							idIncidencia.setText(estado.getKeyMsg());
							mSteppers[3].setState(VerticalStepperItemView.STATE_SELECTED);
							mSteppers[3].setVisibility(View.VISIBLE);
							break;
						case FIN_ENTREGA_FLUJO:
							idFinalizacion.setText(estado.getKeyMsg());
							mSteppers[4].setState(VerticalStepperItemView.STATE_SELECTED);
							break;
					}
				}

			}

			@Override
			public void onFailure(Call<WorkFlow> call, Throwable t) {
				Log.e("onFaillure chamado ", t.getMessage());
			}
		});
		//mSteppers.setVisibility(state == STATE_SELECTED ? View.VISIBLE : View.GONE);

		mNextBtn0 = view.findViewById(R.id.button_next_0);
		mNextBtn0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[0].nextStep();
			}
		});

		view.findViewById(R.id.button_test_error).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mSteppers[0].getErrorText() != null) {
					mSteppers[0].setErrorText(null);
				} else {
					mSteppers[0].setErrorText("Test error!");
				}
			}
		});

		mPrevBtn1 = view.findViewById(R.id.button_prev_1);
		mPrevBtn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[1].prevStep();
			}
		});

		mNextBtn1 = view.findViewById(R.id.button_next_1);
		mNextBtn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[1].nextStep();
			}
		});

		mPrevBtn2 = view.findViewById(R.id.button_prev_2);
		mPrevBtn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[2].prevStep();
			}
		});

		mNextBtn2 = view.findViewById(R.id.button_next_2);
		mNextBtn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Finish!", Snackbar.LENGTH_LONG).show();
			}
		});

		view.findViewById(R.id.btn_change_point_color).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mActivatedColorRes == R.color.material_blue_500) {
					mActivatedColorRes = R.color.material_deep_purple_500;
				} else {
					mActivatedColorRes = R.color.material_blue_500;
				}
				for (VerticalStepperItemView stepper : mSteppers) {
					stepper.setActivatedColorResource(mActivatedColorRes);
				}
			}
		});
		view.findViewById(R.id.btn_change_done_icon).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mDoneIconRes == R.drawable.ic_done_white_16dp) {
					mDoneIconRes = R.drawable.ic_save_white_16dp;
				} else {
					mDoneIconRes = R.drawable.ic_done_white_16dp;
				}
				for (VerticalStepperItemView stepper : mSteppers) {
					stepper.setDoneIconResource(mDoneIconRes);
				}
			}
		});
	}

	private void getListData(final View view,Long idPedido) {

		ClienteService clienteService = retrofit.create(ClienteService.class);

		Log.i("----> FillPedidosSeg", "Inicio obtenerDetalleOrdenPorIdPedido: ");
		Call<List<CarritoDetalle>> listaCarritoDetalle = clienteService.obtenerDetalleOrdenPorIdPedido(idPedido);
		listaCarritoDetalle.enqueue(new Callback<List<CarritoDetalle>>() {
			@Override
			public void onResponse(Call<List<CarritoDetalle>> call, Response<List<CarritoDetalle>> response) {
				Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
				if(response.isSuccessful()) {
					List<CarritoDetalle> carritoDetalles = response.body();
					StringBuffer textoPedido = new StringBuffer();

					ArrayList<ListItemPedidoSeguimiento> results = new ArrayList<>();

					for(CarritoDetalle det : carritoDetalles){
						//textoPedido.append(det.getCatalogoProducto().getNombre() +" Cantidad "+ det.getCantidad());

						ListItemPedidoSeguimiento user1 = new ListItemPedidoSeguimiento();
						user1.setNombre(det.getCatalogoProducto().getNombre());
						user1.setCantidad(det.getCantidad()+"");
						user1.setId(det.getCatalogoProducto().getIdCatalogoProducto()+"");
						results.add(user1);
					}

					ArrayList userList = results;
					final ListView lv = (ListView) view.findViewById(R.id.user_list);
					lv.setAdapter(new CustomListAdapter(getContext(), userList));
					lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							ListItemPedidoSeguimiento user = (ListItemPedidoSeguimiento) lv.getItemAtPosition(position);
							Toast.makeText(getContext(), "Selected :" + " " + user.getId()+", "+ user.getNombre(), Toast.LENGTH_SHORT).show();
						}
					});

					//idDetallPedido.setText(textoPedido);
				}
			}

			@Override
			public void onFailure(Call<List<CarritoDetalle>> call, Throwable t) {
				Log.e("onFaillure chamado ", t.getMessage());
			}
		});
		Log.i("----> FillPedidosSeg" , "Fin obtenerDetalleOrdenPorIdPedido: ");

		//return results;
	}

}
