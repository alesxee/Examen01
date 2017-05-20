package com.example.ariso.planetas.actvities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ariso.planetas.MainApp;
import com.example.ariso.planetas.R;
import com.example.ariso.planetas.adaptador.PlanetaAdapter;
import com.example.ariso.planetas.model.DaoSession;
import com.example.ariso.planetas.model.PLaneta;
import com.example.ariso.planetas.model.PLanetaDao;
import com.example.ariso.planetas.util.Constants;
import com.example.ariso.planetas.util.SnackBarHelper;

import org.greenrobot.greendao.query.Query;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PlanetasActivity extends AppCompatActivity implements PlanetaAdapter.PlanetaClickListener,
        PlanetaAdapter.EditarPlanetaClickListener {

    private static final String TAG  = "PlanetasActivity";

    @BindView(R.id.et_texto)
    EditText mEtTexto;
    @BindView(R.id.btn_agregar)
    Button mBtnAgregar;
    @BindView(R.id.rv_notas)
    RecyclerView mRvNotas;
    @BindView(R.id.tv_lista_notas_vacia)
    TextView mTvListaVacia;

    private Unbinder unbinder;

    private PLanetaDao notaDao;
    private Query<PLaneta> notasQuery;
    private PlanetaAdapter notaAdapter;

    private PLaneta notaEditable;
    private boolean isEditable = false;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        mRvNotas.setHasFixedSize(true);
        mRvNotas.setLayoutManager(new LinearLayoutManager(this));

        //como parametro recibe 'this', 'this' porque las interfaces estan siendo implementadas por el activity.
        notaAdapter = new PlanetaAdapter(this, this);
        mRvNotas.setAdapter(notaAdapter);

        //obtener la session
        DaoSession daoSession = ((MainApp)getApplication()).getDaoSession();
        notaDao = daoSession.getPLanetaDao();

        //Obtener datos
        notasQuery = notaDao.queryBuilder().orderAsc(PLanetaDao.Properties.Texto).build();
        actualizarLista();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //metodo implementado de la interface EditarNotaClickListener
    @Override public void onEditarPlanetaClick(int position) {
        notaEditable = notaAdapter.getPlaneta(position);
        isEditable = true;
        mEtTexto.setText(notaEditable.getTexto());

        int ultimaPosicion = mEtTexto.getText().length();
        mEtTexto.setSelection(ultimaPosicion);

        mBtnAgregar.setText(getString(R.string.registrar_editar_nota));
    }

    //metodo implementado de la interface NotaClickListener
    @Override public void onPlanetaClick(int position) {
        final Long notaId = notaAdapter.getPlaneta(position).getId();
        AlertDialog.Builder alert = new AlertDialog.Builder(PlanetasActivity.this);
        alert.setTitle(R.string.app_name);
        alert.setMessage(R.string.notas_dialog_eliminar_msj);
        alert.setPositiveButton(R.string.dialog_aceptar, new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {
                notaDao.deleteByKey(notaId);
                actualizarLista();
                dialog.dismiss();
            }
        });
        alert.setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @OnClick(R.id.btn_agregar) public void onAgregar(){
        if(validarCampos()){
            if(isEditable){
                editarNota();
                isEditable = false;

                mBtnAgregar.setText(getString(R.string.registrar_agregar_nota));
                mEtTexto.setText(Constants.EMPTY);

            }else{
                agregarPlaneta();
            }
        }
    }




    /** valida el campo texto de la nota.
     * */
    private boolean validarCampos(){
        if(mEtTexto.getText().toString().isEmpty()){
            SnackBarHelper.showWarningMessage(mEtTexto, this, getString(R.string.registrar_validacion_texto));
            return false;
        }
        return true;
    }

    /** Actualiza el adapter y el adapter actualiza el RecyclerView,
     * es usado cada que se ejecuta una operación(agregar, eliminar o editar).
     * */
    private void actualizarLista(){
        List<PLaneta> notas = notasQuery.list();
        if(notas.size()>0){
            mRvNotas.setVisibility(View.VISIBLE);
            mTvListaVacia.setVisibility(View.GONE);
            notaAdapter.setPlanetas(notas);
        }else{
            mRvNotas.setVisibility(View.GONE);
            mTvListaVacia.setVisibility(View.VISIBLE);
        }
    }


    /** edita la nota selecionada de la lista.
     * */
    private void editarNota(){
        notaEditable.setTexto(mEtTexto.getText().toString());
        notaEditable.setDate(new Date());
        notaDao.update(notaEditable);
        SnackBarHelper.showSuccessMessage(mEtTexto, this, getString(R.string.Editar_nota_correcto));
        actualizarLista();
    }

    /** agrega una nota a la base de datos.
     * */
    private void agregarPlaneta(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        PLaneta nota = new PLaneta();
        nota.setTexto(mEtTexto.getText().toString());
        nota.setDate( new Date());
        //retorna el ID del registro si regostro correctamente
        long success = notaDao.insert(nota);
        if(success>0){
            SnackBarHelper.showSuccessMessage(mEtTexto, this, getString(R.string.registrar_mensaje_correcto));
            actualizarLista();
            mEtTexto.setText(Constants.EMPTY);
        }else{
            SnackBarHelper.showErrorMessage(mEtTexto, this, getString(R.string.registrar_mensaje_error));
        }
    }
}
