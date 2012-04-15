package tronic2.useroperations

import javax.swing.JOptionPane

class ObtainingPasswordController {
    def model
    def view

    void mvcGroupInit(Map args) {
        doLater {model.usuarios.addAll(args.user)}
    }

    def salir = {
        destroyMVCGroup('obtainingPassword')
        app.views.tronicApp.view.passwordCombo.requestFocusInWindow()
    }

    def aceptar = { evt = null ->
        doLater {
            if (view.usuarioCombo.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(view.obtainingScreen, "Debe seleccionar un Usuario", "Error", JOptionPane.ERROR_MESSAGE)
            } else {
                def mvc = buildMVCGroup('obtainingPassword2', [user: view.usuarioCombo.getSelectedItem()])
                def pantalla = mvc.view.obtainingScreen2
                pantalla.show()
            }
        }
    }

    def foco1 = { evt = null ->
        doLater {
            if (evt.getComponent().getName().equals('aceptarBoton')) {
                view.obtainingScreen.getRootPane().setDefaultButton(view.aceptarBoton)
            } else if (evt.getComponent().getName().equals('salirBoton')) {
                view.obtainingScreen.getRootPane().setDefaultButton(view.salirBoton)
            } else if (evt.getComponent().getName().equals('usuarioCombo')) {
                view.obtainingScreen.getRootPane().setDefaultButton(view.aceptarBoton)
            }
        }
    }
}
