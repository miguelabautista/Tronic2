package services

import griffon.spock.*

import spock.lang.*
import groovy.sql.Sql
import tronic2.InventarioDBService

class InventarioDBServiceSpec extends IntegrationSpec {
    GriffonApplication app
    InventarioDBService inven = new InventarioDBService()
    @Shared db = Sql.newInstance('jdbc:mysql://localhost:3306/tiendatronic', 'root', 'filan3001', 'com.mysql.jdbc.Driver')
    @Shared listaNombres = ['MOUSE', 'TECLADO', 'MONITOR', 'CABLES']

    def setupSpec() {

        listaNombres.each {
            db.execute("INSERT INTO nombreproducto(nombre) VALUES($it)")
        }
    }

    def cleanupSpec() {
        listaNombres.each {
            db.execute("TRUNCATE TABLE inventario")
            db.execute("TRUNCATE TABLE nombreproducto")
            db.execute("TRUNCATE TABLE usuarios")
        }
    }

    @spock.lang.Ignore
    //@spock.lang.Unroll('listado')
    def 'obtener los nombres de productos'() {
        expect:
        inven.nombreProductos == listaNombres.sort()
    }

    @spock.lang.Ignore
    def 'Ingresar un nuevo nombre de Producto'() {
        expect:
        inven.setNombreProducto(a) == false

        where:

        a = 'Teclado'
    }

    @spock.lang.Ignore
    def 'Obtener el codigo de cada articulo'() {
        expect:
        def articulos = inven.getCodigoArticulos()

        articulos == c

        where:

        c = ['0001', '0002', '0003', '0004']
    }

    @spock.lang.Ignore
    def 'crear un nuevo articulo'() {
        expect:
        inven.setNuevoarticulo(nombre, codigo, precioUnitario, stock, precioSinIva, pvp, descripcion, precioLotes, nte, adicional, factura, n_precio) == false

        where:

        nombre      | codigo | precioUnitario | stock | precioSinIva | pvp  | descripcion | precioLotes | nte | adicional | factura | n_precio

        "eso mismo" | "0004" | 125.23         | 23    | 32.1         | 43.2 | "caraotas"  | 323         | ""  | "N/A"     | "N/A"   | "N/A"
    }

    @spock.lang.Ignore
    def 'borrar articulo'() {

        expect:
        inven.deleteArticulo("0001") == false
    }

    def 'actualizar articulo'() {

        expect:
        inven.updateArticulo('pantera', '0001', 99999, 9, 999999, 99999, 'pantera', 23, 'N?A', 'N?A', "N?A", 'N?A') == false
        def respuesta
        db.eachRow("SELECT * FROM inventario WHERE codigo='0001'") {
            respuesta = it.nombre
        }
        respuesta == 'caraotas'
    }
}
