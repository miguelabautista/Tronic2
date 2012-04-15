package tronic2

import groovy.sql.Sql

class InventarioDBService {
    def getNombreProductos() {
        withSql { dataSourcename, sql ->
            TreeSet nombres = []
            sql.eachRow("SELECT * FROM nombreproducto") {
                nombres.add(it.nombre)
            }
            return nombres
        }
    }

    def setNombreProducto(String nombre) {
        withSql {dataSourceName, sql ->
            def respuesta = sql.execute("INSERT INTO nombreproducto(nombre) VALUES($nombre)")
            return respuesta
        }
    }

    def getCodigoArticulos() {
        withSql { dataSourceName, sql ->
            def respuesta = []
            sql.eachRow("SELECT * FROM inventario") {
                respuesta.add(it.Codigo)
            }
            return respuesta
        }
    }

    def setNuevoarticulo(def nombre, def codigo, def precioUnitario, def stock, def precioSinIva, def pvp, def descripcion, def precioLotes, def nte, def adicional, def factura, def n_precio) {
        withSql { dataSourceName, sql ->
            def respuesta
            respuesta = sql.execute("INSERT INTO inventario(nombre,codigo,precioUnitario,stock,precioSinIva,pvp,descripcion,precioLotes,nte,adicional,factura,n_precio) VALUES($nombre,$codigo,$precioUnitario,$stock,$precioSinIva,$pvp,$descripcion,$precioLotes,$nte,$adicional,$factura,$n_precio)")
            return respuesta
        }
    }

    def getInventario() {
        withSql {dataSourcename, sql ->
            def lista = []
            sql.eachRow("SELECT * FROM inventario") {
                lista << [codigo: it.codigo, nombre: it.nombre,
                        descripcion: it.descripcion, stock: it.stock,
                        precioUnitario: it.precioUnitario, precioSinIva: it.precioSinIva,
                        pvp: it.pvp, precioLotes: it.precioLotes, nte: it.nte, adicional: it.adicional,
                        nPrecio: it.n_Precio]
            }
            return lista
        }
    }

    def deleteArticulo(String codigo) {
        withSql {dataSourceName, Sql sql ->
            def respuesta = sql.execute("DELETE FROM inventario WHERE codigo = $codigo")
            return respuesta
        }
    }

    def updateArticulo(def nombre, def codigo, def precioUnitario, def stock, def precioSinIva, def pvp, def descripcion, def precioLotes, def nte, def adicional, def factura, def n_precio) {
        withSql { dataSourceName, sql ->
            def respuesta
            respuesta = sql.execute("UPDATE inventario SET nombre = $nombre, precioUnitario = $precioUnitario, stock =$stock, precioSinIva = $precioSinIva, pvp=$pvp, descripcion = $descripcion, precioLotes = $precioLotes, nte = $nte, adicional =$adicional, factura = $factura, n_precio = $n_precio WHERE codigo = $codigo")
            return respuesta
        }
    }

}