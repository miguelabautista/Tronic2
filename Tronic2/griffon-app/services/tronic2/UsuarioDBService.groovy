package tronic2

class UsuarioDBService {
    /**
     * usuarios registrados
     *
     * @return List usuarios
     */
    def getUsuario() {
        withSql { dataSourceName, sql ->
            def tmpList = []
            def usuarioSet = sql.dataSet("usuarios")
            usuarioSet.each {
                tmpList << it.nombre
            }
            return tmpList
        }
    }
    /**
     * clave del usuario ingresado
     *
     * @param usuario
     * @return clave
     */
    def getClave(String usuario) {
        withSql { dataSourceName, sql ->
            String clave
            sql.eachRow("SELECT * FROM usuarios WHERE nombre = $usuario ") {
                clave = it.clave
            }
            return clave
        }
    }
    /**
     * preguntas asociadas con el usuario
     * @param usuario
     * @return clave del usuario
     */
    def getPreguntas(String usuario) {
        withSql { dataSourcename, sql ->
            def userId
            Set preguntas = new TreeSet()
            sql.eachRow("SELECT * FROM usuarios WHERE nombre = $usuario") {
                userId = it.idClave
            }
            sql.eachRow("SELECT * FROM preguntas WHERE usuarios_IDClave = $userId ") {
                preguntas.add(it.preguntas)
            }
            return preguntas
        }
    }
    /**
     * respuesta asociada a la pregunta
     * @param pregunta
     * @return respuesta
     */
    def getRespuesta(String pregunta) {
        withSql {datasourceName, sql ->
            def respuesta
            sql.eachRow("SELECT * FROM preguntas WHERE preguntas = $pregunta") {
                respuesta = it.respuestas
            }
            return respuesta
        }
    }
    /**
     * eliminia la pregunta
     * @param pregunta
     * @return false - si se elimino la pregunta \n true - si no se ha eliminado
     */
    def eliminarPregunta(String pregunta) {
        withSql {datasourceName, sql ->
            def respuesta = sql.execute("DELETE FROM preguntas WHERE preguntas = $pregunta")
            return respuesta
        }
    }
    /**
     * cambia la respuesta
     * @param pregunta
     * @param respuesta
     * @return false - si se cambio la respuesta \n true - si no se cambio la respuesta
     */
    def cambiarRespuesta(String pregunta, String respuesta) {
        withSql {datasourceName, sql ->
            def response = sql.execute("UPDATE preguntas SET respuestas = $respuesta WHERE preguntas = $pregunta")
            return response
        }
    }
    /**
     * crea una respuesta que se asocia a un usuario especifico
     *
     * @param pregunta
     * @param respuesta
     * @param usuario
     * @return false - si se creo la pregunta \n true- si no se creo la pregunta
     */
    def crearPregunta(String pregunta, String respuesta, String usuario) {
        withSql { dataSourceName, sql ->
            def id
            sql.eachRow("SELECT * FROM usuarios WHERE nombre = $usuario ") {
                id = it.idclave
            }
            def res = sql.execute("INSERT INTO preguntas (preguntas,respuestas,usuarios_Idclave) VALUES($pregunta,$respuesta,$id)")
            return res
        }
    }
    /**
     * cambia la clave del usuari
     * @param usuario
     * @param clave
     * @return false - si se cambio la clave \n true- si no se cambio la clave
     */
    def cambiarClave(String usuario, String clave) {
        withSql {dataSourceName, sql ->
            def re
            re = sql.execute("UPDATE usuarios SET clave = $clave WHERE nombre = $usuario ")
            return re
        }
    }
}