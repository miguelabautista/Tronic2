package tronic2.useroperations

import java.awt.Color
import java.awt.event.MouseEvent

class UsuariosController {
    // these will be injected by Griffon
    def model
    def view

    def salir = {evt = null ->
        destroyMVCGroup('usuarios')
    }

    def labelAction = { evt = null ->
        edt {
            if (evt.getButton() == MouseEvent.BUTTON1) {
                if (evt.getComponent().getName().equals("cuentaLabel")) {
                    view.cuentaLabel.setForeground(Color.red)
                } else if (evt.getComponent().getName().equals("cuentaTipoLabel")) {
                    view.cuentaTipoLabel.setForeground(Color.red)
                } else if (evt.getComponent().getName().equals("passwordLabel")) {
                    view.passwordLabel.setForeground(Color.red)
                    def mvc = buildMVCGroup('changePassword', [user: app.models.principalScreen.usuario])
                    def pantalla = mvc.view.passwordScreen
                    pantalla.show()
                } else if (evt.getComponent().getName().equals("preguntasLabel")) {
                    view.preguntasLabel.setForeground(Color.red)
                    def mvc = buildMVCGroup('changeQuestions')
                    def pantalla = mvc.view.questionsScreen
                    pantalla.show()
                }
            }
        }

    }
}
