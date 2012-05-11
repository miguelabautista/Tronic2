package tronic2

import javax.swing.JOptionPane

class PrincipalScreenController {
    def model
    def view

    void mvcGroupInit(Map args) {
        model.usuario = args.user
    }
    //Prueba
    def usuarios = { evt = null ->
        def mvc = buildMVCGroup('usuarios')
        def pantalla = mvc.view.usuariosScreen
        pantalla.show()
    }

    def salir = { evt = null ->
        int respuesta = JOptionPane.showConfirmDialog(view.firstScreen, "Desea cerrar la aplicacion?", "Aviso", JOptionPane.YES_NO_OPTION)
        if (respuesta.equals(JOptionPane.YES_OPTION)) {
            app.shutdown()
        }
    }

    def ingresarArticulo = { evt = null ->
        def mvc = buildMVCGroup('newArticle', [own: view.firstScreen])
        def pantalla = mvc.view.newArticleScreen
        pantalla.show()
    }

    def administracionInventario = { evt = null ->
        def mvc = buildMVCGroup('administracion')
        def pantalla = mvc.view.administracionScreen
        pantalla.show()
    }
}
