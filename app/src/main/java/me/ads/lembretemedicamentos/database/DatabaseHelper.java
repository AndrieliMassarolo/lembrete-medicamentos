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
import me.ads.lembretemedicamentos.medico.Medico;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lembretemedicamentos";
    private static final String TABLE_MEDICAMENTOS = "medicamentos";
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String TABLE_MEDICOS = "medicos";
    private static final String TABLE_RECEITAS = "receitas";


    private static final String CREATE_TABLE_MEDICAMENTOS = "CREATE TABLE " + TABLE_MEDICAMENTOS + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100)," +
            "dosagem VARCHAR(100), " +
            "tipo VARCHAR(100), " +
            "posologia VARCHAR(100), " +
            "horario VARCHAR(100), " +
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

    private static final String CREATE_TABLE_MEDICOS = "CREATE TABLE " + TABLE_MEDICOS + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100)," +
            "crm VARCHAR(10), " +
            "telefone VARCHAR(15), " +
            "especialidade VARCHAR(100))" ;

    private static final String DROP_TABLE_MEDICOS = "DROP TABLE IF EXISTS " + TABLE_MEDICOS;

    private static final String CREATE_TABLE_RECEITAS = "CREATE TABLE " + TABLE_RECEITAS + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "medicamento VARCHAR(100)," +
            "medico VARCHAR(100), " +
            "data VARCHAR(100), " +
            "validade VARCHAR(100))";

    private static final String DROP_TABLE_RECEITAS = "DROP TABLE IF EXISTS " + TABLE_RECEITAS;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 6);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // criação das tabelas
        db.execSQL(CREATE_TABLE_MEDICAMENTOS);
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_MEDICOS);
        db.execSQL(CREATE_TABLE_RECEITAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // exclusão das tabelas
        db.execSQL(DROP_TABLE_MEDICAMENTOS);
        db.execSQL(DROP_TABLE_USUARIOS);
        db.execSQL(DROP_TABLE_MEDICOS);
        db.execSQL(DROP_TABLE_RECEITAS);
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
        cv.put("horario", m.getHorario());
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
        cv.put("horario", m.getHorario());
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
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "dosagem", "tipo", "posologia", "horario", "tempotratamento"};
        Cursor data = db.query(TABLE_MEDICAMENTOS, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarMedicamentos, R.id.textViewNomeListarMedicamentos, R.id.textViewDosagemListarMedicamentos, R.id.textViewTipoListarMedicamentos,
                R.id.textViewPosologiaListarMedicamentos, R.id.textViewHorarioListarMedicamentos, R.id.textViewTempotratamentoListarMedicamentos};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.medicamentos_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Medicamentos getByIdMedicamentos(int id) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String[] columns = {"_id", "nome","dosagem", "tipo", "posologia", "horario", "tempotratamento"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_MEDICAMENTOS, columns, "_id = ?", args, null, null, null);
        data.moveToFirst();
        Medicamentos m = new Medicamentos();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setDosagem(data.getString(2));
        m.setTipo(data.getString(3));
        m.setPosologia(data.getString(4));
        m.setHorario(data.getString(5));
        m.setTempotratamento(data.getString(6));
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

    /* Início CRUD Medico */
    public long createMedico(Medico m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("crm", m.getCRM());
        cv.put("telefone", m.getTelefone());
        cv.put("especialidade", m.getEspecialidade());
        long id = db.insert(TABLE_MEDICOS, null, cv);
        db.close();
        return id;
    }

    public long updateMedicos (Medico m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("crm", m.getCRM());
        cv.put("telefone", m.getTelefone());
        cv.put("especialidade", m.getEspecialidade());
        long id = db.update(TABLE_MEDICOS, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public long deleteMedico (Medico m) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_MEDICOS, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public Medico getByIdMedico (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "crm", "telefone", "especialidade"};
        Cursor data = db.query(TABLE_MEDICOS, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Medico m = new Medico();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setCRM(data.getString(2));
        m.setTelefone(data.getString(3));
        m.setEspecialidade(data.getString(4));
        data.close();
        db.close();
        return m;
    }

    public void getAllMedico (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "telefone", "especialidade"};
        Cursor data = db.query(TABLE_MEDICOS, columns, null, null, null, null, null);
        int[] to = {R.id.textViewIdListMedico, R.id.textViewNomeListMedico, R.id.textViewTelefoneListMedico, R.id.textViewEspecialidadeListMedico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.medico_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* Fim CRUD Medicos */

    /* Início CRUD Receitas */

//    public long createReceitas(Receitas r) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("medicamento", r.getMedicamento());
//        cv.put("medico", r.getMedico());
//        cv.put("data", r.getData());
//        cv.put("validade", r.getValidade());
//        long id = db.insert(TABLE_RECEITAS, null, cv);
//        db.close();
//        return id;
//    }
//
//    public long updateReceitas(Receitas r) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("medicamento", r.getMedicamento());
//        cv.put("medico", r.getMedico());
//        cv.put("data", r.getData());
//        cv.put("validade", r.getValidade());
//        long id = db.update(TABLE_RECEITAS, cv,
//                "_id = ?", new String[]{String.valueOf(r.getId())});
//        db.close();
//        return id;
//    }
//
//    public long deleteReceitas(Receitas r) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        long id = db.delete(TABLE_RECEITAS,
//                "_id = ?",
//                new String[]{String.valueOf(r.getId())});
//        db.close();
//        return id;
//    }
//
//    public void getAllReceitas(Context context, ListView lv) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {"_id", "medicamento", "medico", "data", "validade"};
//        Cursor data = db.query(TABLE_RECEITAS, columns, null, null, null, null, "data");
//        int[] to = {R.id.textViewIdListarReceitas, R.id.textViewMedicamentoListarReceitas, R.id.textViewMedicoListarReceitas, R.id.textViewDataListarReceitas, R.id.textViewValidadeListarReceitas};
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
//                R.layout.receitas_item_list_view, data, columns, to, 0);
//        lv.setAdapter(simpleCursorAdapter);
//        db.close();
//    }
//
//    public Receitas getByIdReceitas(int id) {
//        SQLiteDatabase db =  this.getReadableDatabase();
//        String[] columns = {"_id", "medicamento","medico", "data", "validade"};
//        String[] args = {String.valueOf(id)};
//        Cursor data = db.query(TABLE_RECEITAS, columns, "_id = ?", args, null, null, null);
//        data.moveToFirst();
//        Receitas r = new Receitas();
//        r.setId(data.getInt(0));
//        r.setNome(data.getString(1));
//        r.setDosagem(data.getString(2));
//        m.setTipo(data.getString(3));
//        m.setPosologia(data.getString(4));
//        m.setTempotratamento(data.getString(5));
//        data.close();
//        db.close();
//        return m;
//    }
//    /* Fim CRUD Receitas */

}