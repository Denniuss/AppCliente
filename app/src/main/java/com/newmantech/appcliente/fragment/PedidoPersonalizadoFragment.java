package com.newmantech.appcliente.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.AdapterPedidoPersonalizado;
import com.newmantech.appcliente.model.EntityProductoPersonalizado;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PedidoPersonalizadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PedidoPersonalizadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidoPersonalizadoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lvMenuPedidosPer;
    private GridView gvPedidosPer;
    private AdapterPedidoPersonalizado adapter;
    private ArrayList<EntityProductoPersonalizado> aProductoPersonalizado;

    private OnFragmentInteractionListener mListener;

    public PedidoPersonalizadoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pedido_personalizado, container, false);

        lvMenuPedidosPer = v.findViewById(R.id.lvMenuPedidosPer);
        gvPedidosPer = v.findViewById(R.id.gvPedidosPer);
        aProductoPersonalizado = new ArrayList<>();
        setProductos();

        adapter = new AdapterPedidoPersonalizado(aProductoPersonalizado, getContext());
        gvPedidosPer.setAdapter(adapter);
        return v;
    }

    public void setProductos(){
        aProductoPersonalizado.add(new EntityProductoPersonalizado(3,"Piscos Personalizados Grabados en bajo relieve",true,
                "https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg"));
        aProductoPersonalizado.add(new EntityProductoPersonalizado(2,"Piscos Personalizados Pavonados y Serigrafiados",true,
                "https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg"));
        aProductoPersonalizado.add(new EntityProductoPersonalizado(1,"Piscos Personalizados Etiquetados",true,
                "https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg"));
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedidoPersonalizadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PedidoPersonalizadoFragment newInstance(String param1, String param2) {
        PedidoPersonalizadoFragment fragment = new PedidoPersonalizadoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
