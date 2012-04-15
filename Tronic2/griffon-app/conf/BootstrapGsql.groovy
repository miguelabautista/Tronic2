import groovy.sql.Sql

class BootstrapGsql {
    def init = { String dataSourceName = 'default', Sql sql ->
        sql.execute("INSERT INTO usuarios(idClave,nombre,clave) VALUES(1,'ADMINISTRADOR','a');")
        sql.execute("INSERT INTO preguntas(preguntas,respuestas,usuarios_Idclave) VALUES('como se llama tu perro','fox',1);")
        sql.execute("INSERT INTO nombreproducto(nombre) VALUES('TECLADO')")
        sql.execute("INSERT INTO nombreproducto(nombre) VALUES('MONITOR')")
        sql.execute("INSERT INTO nombreproducto(nombre) VALUES('MOUSE')")
        /* sql.execute("INSERT INTO inventario(nombre,codigo,precioUnitario,stock,precioSinIva,pvp,descripcion,nte,adicional,factura,n_precio,precioLotes) VALUES('monitor','0001',2500,35,2534,2323,'32 pulgadas','N/A','N/A','N/A','N/A',45)")
       sql.execute("INSERT INTO inventario(nombre,codigo,precioUnitario,stock,precioSinIva,pvp,descripcion,nte,adicional,factura,n_precio,precioLotes) VALUES('teclado','0002',3200,43,543,233434,'microsoft','N/A','N/A','N/A','N/A',23)")
       sql.execute("INSERT INTO inventario(nombre,codigo,precioUnitario,stock,precioSinIva,pvp,descripcion,nte,adicional,factura,n_precio,precioLotes) VALUES('mouse','0003',334,4334,54343,34233434,'microsoft','N/A','N/A','N/A','N/A',23)")
        */
    }

    def destroy = { String dataSourceName = 'default', Sql sql ->
        sql.execute("DELETE FROM preguntas WHERE preguntas = 'como se llama tu perro' ")
        sql.execute("DELETE FROM usuarios WHERE nombre = 'ADMINISTRADOR'")
        sql.execute("TRUNCATE TABLE nombreproducto")
        // sql.execute("TRUNCATE TABLE inventario")
    }

}
