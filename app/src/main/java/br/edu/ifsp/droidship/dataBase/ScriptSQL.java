package br.edu.ifsp.droidship.dataBase;

/**
 * Created by Eduardo on 07/05/2016.
 */
public class ScriptSQL {
    public static String getCreateScore(){

        StringBuilder SQLBuilder = new StringBuilder();
        SQLBuilder.append("CREATE TABLE IF NOT EXISTS HIGHSCORE( ");
        SQLBuilder.append("_id       INTEGER         NOT NULL ");
        SQLBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        SQLBuilder.append("NOME         VARCHAR(50), ");
        SQLBuilder.append("PONTOS       INTEGER ");
        SQLBuilder.append(");");

        return SQLBuilder.toString();
    }
}
