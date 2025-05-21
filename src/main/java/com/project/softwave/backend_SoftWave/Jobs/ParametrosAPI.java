package com.project.softwave.backend_SoftWave.Jobs;

public class ParametrosAPI {

    private static String PARAMETRO_PROCESSO = "";
    private static String PARAMETRO_PARTE = "";
    private static String PARAMETRO_CPF = "";
    private static String PARAMETRO_CNPJ = "";
    private static String PARAMETRO_RG = "";
    private static String PARAMETRO_ADVOGADO = "";
    private static String PARAMETRO_CARTA_PRECATORIA = "";
    private static String PARAMETRO_DOCUMENTO_DELEGACIA = "";
    private static String PARAMETRO_CDA = "";
    private static String PARAMETRO_PAGINA = "1";
    private static String PARAMETRO_OAB = "";

    public static void resetParametros() {
        PARAMETRO_PROCESSO = "";
        PARAMETRO_PARTE = "";
        PARAMETRO_CPF = "";
        PARAMETRO_CNPJ = "";
        PARAMETRO_RG = "";
        PARAMETRO_ADVOGADO = "";
        PARAMETRO_CARTA_PRECATORIA = "";
        PARAMETRO_DOCUMENTO_DELEGACIA = "";
        PARAMETRO_CDA = "";
        PARAMETRO_OAB = "";
    }

    public static String getTOKEN() {
        return "_43zgUNqJEQwIMgwnIsEeJrRBTy6x0cQZsVgZDap";
    }

    public static String getTIMEOUT() {
        return "600";
    }

    public static String getParametroProcesso() {
        return PARAMETRO_PROCESSO;
    }

    public static void setParametroProcesso(String parametroProcesso) {
        PARAMETRO_PROCESSO = parametroProcesso;
    }

    public static String getParametroParte() {
        return PARAMETRO_PARTE;
    }

    public static void setParametroParte(String parametroParte) {
        PARAMETRO_PARTE = parametroParte;
    }

    public static String getParametroCpf() {
        return PARAMETRO_CPF;
    }

    public static void setParametroCpf(String parametroCpf) {
        PARAMETRO_CPF = parametroCpf;
    }

    public static String getParametroCnpj() {
        return PARAMETRO_CNPJ;
    }

    public static void setParametroCnpj(String parametroCnpj) {
        PARAMETRO_CNPJ = parametroCnpj;
    }

    public static String getParametroRg() {
        return PARAMETRO_RG;
    }

    public static void setParametroRg(String parametroRg) {
        PARAMETRO_RG = parametroRg;
    }

    public static String getParametroAdvogado() {
        return PARAMETRO_ADVOGADO;
    }

    public static void setParametroAdvogado(String parametroAdvogado) {
        PARAMETRO_ADVOGADO = parametroAdvogado;
    }

    public static String getParametroCartaPrecatoria() {
        return PARAMETRO_CARTA_PRECATORIA;
    }

    public static void setParametroCartaPrecatoria(String parametroCartaPrecatoria) {
        PARAMETRO_CARTA_PRECATORIA = parametroCartaPrecatoria;
    }

    public static String getParametroDocumentoDelegacia() {
        return PARAMETRO_DOCUMENTO_DELEGACIA;
    }

    public static void setParametroDocumentoDelegacia(String parametroDocumentoDelegacia) {
        PARAMETRO_DOCUMENTO_DELEGACIA = parametroDocumentoDelegacia;
    }

    public static String getParametroCda() {
        return PARAMETRO_CDA;
    }

    public static void setParametroCda(String parametroCda) {
        PARAMETRO_CDA = parametroCda;
    }

    public static String getParametroPagina() {
        return PARAMETRO_PAGINA;
    }

    public static void setParametroPagina(String parametroPagina) {
        PARAMETRO_PAGINA = parametroPagina;
    }

    public static String getParametroOab() {
        return PARAMETRO_OAB;
    }

    public static void setParametroOab(String parametroOab) {
        PARAMETRO_OAB = parametroOab;
    }
}
