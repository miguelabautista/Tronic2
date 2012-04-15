package tronic2.useroperations

import javax.swing.JOptionPane

class ObtainingPassword2Controller {
    def model
    def view
    def usuarioDBService

    void mvcGroupInit(Map args) {
        model.usuario = args.user
        def preguntas = usuarioDBService.getPreguntas(model.usuario)
        edt {model.preguntas.addAll(preguntas)}
    }

    def aceptarPregunta = { evt = null ->
        doLater {
            if (view.preguntasCombo.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(view.obtainingScreen2, "debe seleccionar una pregunta", "Error", JOptionPane.ERROR_MESSAGE)
            } else if (view.preguntasText.getText().equals("")) {
                JOptionPane.showMessageDialog(view.obtainingScreen2, "Debe ingresar una respuesta", "Error", JOptionPane.ERROR_MESSAGE)
                view.preguntasText.requestFocusInWindow()
            } else {
                doOutside {
                    String pregunta = (String) view.preguntasCombo.getSelectedItem()
                    String respuesta = view.preguntasText.getText()
                    String resultado = usuarioDBService.getRespuesta(pregunta)
                    if (respuesta.equals(resultado)) {
                        def clave = usuarioDBService.getClave(model.usuario)
                        JOptionPane.showMessageDialog(view.obtainingScreen2, "su clave es: $clave", "aviso", JOptionPane.INFORMATION_MESSAGE)
                        clave = null
                        destroyMVCGroup('obtainingPassword2')
                        app.views.obtainingPassword.view.obtainingScreen.setVisible(false)
                        destroyMVCGroup('obtainingPassword')
                        app.views.bienvenidoScreen.passwordCombo.requestFocusInWindow()
                    } else {
                        JOptionPane.showMessageDialog(view.obtainingScreen2, "Respuesta incorrecta", "error", JOptionPane.ERROR_MESSAGE)
                        view.preguntasText.setText('')
                        view.preguntasText.requestFocusInWindow()
                    }
                }
            }
        }
    }

    def foco2 = { evt = null ->
        doLater {
            if (evt.getComponent().getName().equals('aceptarBotonPregunta')) {
                view.obtainingScreen2.getRootPane().setDefaultButton(view.aceptarBotonPregunta)
            } else if (evt.getComponent().getName().equals('salirBotonPregunta')) {
                view.obtainingScreen2.getRootPane().setDefaultButton(view.salirBotonPregunta)
            } else if (evt.getComponent().getName().equals('preguntasCombo')) {
                view.obtainingScreen2.getRootPane().setDefaultButton(view.aceptarBotonPregunta)
            }
        }
    }

    def salir = {
        destroyMVCGroup('obtainingPassword2')
    }
}
