package me.ads.lembretemedicamentos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import me.ads.lembretemedicamentos.R;

import me.ads.lembretemedicamentos.medicamentos.Medicamentos;
import me.ads.lembretemedicamentos.usuarios.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lembretemedicamentos";
    private static final String TABLE_MEDICAMENTOS = "medicamentos";
    private static final String TABLE_USUARIOS = "usuarios";

    private static final String CREATE_TABLE_MEDICAMENTOS = "CREATE TABLE " + TABLE_MEDICAMENTOS + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100)," +
            "dosagem VARCHAR(100), " +
            "tipo VARCHAR(100), " +
            "posologia VARCHAR(100), " +
            "tempotratamento VARCHAR(100));";

    private static final String DROP_TABLE_MEDICAMENTOS = "DROP TABLE IF EXISTS " + TABLE_MEDICAMENTOS;

    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100)," +
            "email VARCHAR(100), " +
            "celular VARCHAR(100), " +
            "sexo VARCHAR(100), " +
            "data_nascimento DATE);";

    private static final String DROP_TABLE_USUARIOS = "DROP TABLE IF EXISTS " + TABLE_USUARIOS;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_MEDICAMENTOS);
            db.execSQL(CREATE_TABLE_USUARIOS);
        } catch (Throwable ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MEDICAMENTOS);
        db.execSQL(DROP_TABLE_USUARIOS);
        onCreate(db);

    }

    /* Início CRUD Medicamentos */
    public long createMedicamentos(Medicamentos m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("dosagem", m.getDosagem());
        cv.put("tipo", m.getTipo());
        cv.put("posologia", m.getPosologia());
        cv.put("tempotratamento", m.getTempotratamento());
        long id = db.insert(TABLE_MEDICAMENTOS, null, cv);
        db.close();
        return id;
    }

    public long updateMedicamentos(Medicamentos m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("dosagem", m.getDosagem());
        cv.put("tipo", m.getTipo());
        cv.put("posologia", m.getPosologia());
        cv.put("tempotratamento", m.getTempotratamento());
        long id = db.update(TABLE_MEDICAMENTOS, cv,
                "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public long deleteMedicamentos(Medicamentos m) {
        SQLiteDatabase db = this.getWritableDatabase();

        long id = db.delete(TABLE_MEDICAMENTOS,
                "_id = ?",
                new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public void getAllMedicamentos(Context context, ListView lv) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns = {"_id", "nome", "dosagem", "tipo", "posologia", "tempotratamento"};
            Cursor data = db.query(TABLE_MEDICAMENTOS, columns, null, null, null, null, "nome");
            int[] to = {R.id.textViewIdListarMedicamentos, R.id.textViewNomeListarMedicamentos, R.id.textViewDosagemListarMedicamentos, R.id.textViewTipoListarMedicamentos, R.id.textViewPosologiaListarMedicamentos, R.id.textViewTempotratamentoListarMedicamentos};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                    R.layout.medicamentos_item_list_view, data, columns, to, 0);
            lv.setAdapter(simpleCursorAdapter);
            db.close();
        } catch (Throwable ex) {
            System.out.println(ex);
        }
    }
    public Medicamentos getByIdMedicamentos(int id) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String[] columns = {"_id", "nome","dosagem", "tipo", "posologia", "tempotratamento"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_MEDICAMENTOS, columns, "_id = ?", args, null, null, null);
        data.moveToFirst();
        Medicamentos m = new Medicamentos();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setDosagem(data.getString(2));
        m.setTipo(data.getString(3));
        m.setPosologia(data.getString(4));
        m.setTempotratamento(data.getString(5));
        data.close();
        db.close();
        return m;
    }
    /* Fim CRUD Medicamentos */

    /* Início CRUD Usuarios */
    public long createUsuario(Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", u.getNome());
        cv.put("email", u.getEmail());
        cv.put("celular", u.getCelular());
        cv.put("sexo", u.getSexo());
        cv.put("data_nascimento", u.getData_nascimento());
        long id = db.insert(TABLE_USUARIOS, null, cv);
        db.close();
        return id;
    }

    public long updateUsuario (Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", u.getNome());
        cv.put("email", u.getEmail());
        cv.put("celular", u.getCelular());
        cv.put("sexo", u.getSexo());
        cv.put("data_nascimento", u.getData_nascimento());
        long id = db.update(TABLE_USUARIOS, cv, "_id = ?", new String[]{String.valueOf(u.getId())});
        db.close();
        return id;
    }

    public long deleteUsuario (Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_USUARIOS, "_id = ?", new String[]{String.valueOf(u.getId())});
        db.close();
        return id;
    }

    public Usuario getByIdUsuario (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "email", "celular", "sexo", "data_nascimento"};
        Cursor data = db.query(TABLE_USUARIOS, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Usuario u = new Usuario();
        u.setId(data.getInt(0));
        u.setNome(data.getString(1));
        u.setEmail(data.getString(2));
        u.setCelular(data.getString(3));
        u.setSexo(data.getString(4));
        u.setData_nascimento(data.getString(5));
        data.close();
        db.close();
        return u;
    }

    public void getAllUsuario (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "celular"};
        Cursor data = db.query(TABLE_USUARIOS, columns, null, null, null, null, null);
        int[] to = {R.id.textViewIdListUsuario, R.id.textViewNomeListUsuario, R.id.textViewCelularListUsuario};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.usuario_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* Fim CRUD Usuarios */

}