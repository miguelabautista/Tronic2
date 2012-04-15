package tronic2.useroperations

import java.awt.Color
import javax.swing.JOptionPane

class ChangeQuestionsController {
    def model
    def view
    def usuarioDBService

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    def salir = { evt = null ->
        app.views.usuarios.view.preguntasLabel.setForeground(Color.black)
        destroyMVCGroup('changeQuestions')
    }

    def aceptar = { evt = null ->
        doOutside {
            //TODO cambiar
            char[] clave = usuarioDBService.getClave(app.models.principalScreen.usuario).toCharArray()
            char[] respuesta = view.claveField.getPassword()
            doLater {
                if (Arrays.equals(clave, respuesta)) {
                    def mvc = buildMVCGroup('changeQuestions2')
                    def pantalla = mvc.view.questionsScreen2
                    pantalla.show()
                    view.questionsScreen.setVisible(false)
                } else {
                    JOptionPane.showMessageDialog(view.questionsScreen, "Clave incorrecta", 'Error', JOptionPane.ERROR_MESSAGE)
                    view.claveField.setText('')
                    view.claveField.requestFocusInWindow()
                }
            }
            Arrays.fill(clave, '0' as char)
            Arrays.fill(respuesta, '0' as char)
        }
    }

    def foco2 = { evt = null ->
        doLater {
            if (evt.getComponent().getName().equals('aceptarButton')) {
                view.questionsScreen.getRootPane().setDefaultButton(view.aceptarButton)
            } else if (evt.getComponent().getName().equals('cancelarButton')) {
                view.questionsScreen.getRootPane().setDefaultButton(view.cancelarButton)
            } else if (evt.getComponent().getName().equals('claveField')) {
                view.questionsScreen.getRootPane().setDefaultButton(view.aceptarButton)
            }
        }
    }
}
