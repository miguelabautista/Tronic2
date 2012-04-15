package services

import griffon.test.GriffonUnitTestCase
import groovy.sql.Sql
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import tronic2.UsuarioDBService

class UsuarioDBServiceTests extends GriffonUnitTestCase {
    GriffonApplication app
    UsuarioDBService ser = new UsuarioDBService()
    def db

    @Before
    public void setUp() {
        db = Sql.newInstance('jdbc:mysql://localhost:3306/tiendatronic', 'root', 'filan3001', 'com.mysql.jdbc.Driver')
        db.execute("INSERT INTO preguntas(preguntas,respuestas,usuarios_Idclave) VALUES('como se llama tu perro','fox',1);")
    }

    @After
    public void tearDown() {
        db.execute("DELETE FROM preguntas WHERE preguntas = 'como se llama tu perro';")
    }


    @Test
    void getClave() {
        def clave = ser.getClave("ADMINISTRADOR")
        assertEquals('a', clave)
    }

    @Ignore
    @Test
    void getRespuesta() {
        def respuesta = ser.getRespuesta("como se llama tu perro")
        assertEquals('fox', respuesta)
    }

    @Ignore
    @Test
    void borrarPregunta() {
        def respuesta = ser.eliminarPregunta('como se llama tu perro')
        assertFalse(respuesta)
    }

    @Ignore
    @Test
    void cambiaRespuesta() {
        ser.cambiarRespuesta('como se llama tu perro', 'furia')
        def respuesta = ser.getRespuesta('como se llama tu perro')
        assertEquals('furia', respuesta)
    }

    @Ignore
    @Test
    void crearPregunta() {
        assertFalse ser.crearPregunta("como se llama tu madre", "betty", 'ADMINISTRADOR')
    }

    @Ignore
    @Test
    void cambiarClave() {
        ser.cambiarClave('ADMINISTRADOR', '123')
        def respuesta = ser.getClave("ADMINISTRADOR")
        assertEquals("cambio de password incorrecto", "123", respuesta)
    }
}
