package com.example.ariso.planetas;

import android.app.Application;

import com.example.ariso.planetas.model.DaoMaster;
import com.example.ariso.planetas.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by ariso on 20/05/2017.
 */

public class MainApp extends Application{

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override public void onCreate() {
        super.onCreate();
        String nombreBaseDeDatos = ENCRYPTED ? "notas-db-encriptado" : "planetas-db";
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, nombreBaseDeDatos);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("mi_clave_secreta") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
