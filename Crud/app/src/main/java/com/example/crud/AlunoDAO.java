package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserir(Alunos aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        return banco.insert("aluno", null,values);
    }

    public List<Alunos> obterTodos(){
        List<Alunos> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno",new String[]{"id","nome","cpf","telefone"},
                null,null,null,null,null);
        while (cursor.moveToNext()) {
            Alunos a = new Alunos();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            alunos.add(a);
        }
        return alunos;
    }

    public void excluir(Alunos a){
        banco.delete("aluno","id=?",new String[]{a.getId().toString()});
    }

    public void atualizar(Alunos aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        banco.update("aluno",values,"id=?", new String[]{aluno.getId().toString()});

    }

}
