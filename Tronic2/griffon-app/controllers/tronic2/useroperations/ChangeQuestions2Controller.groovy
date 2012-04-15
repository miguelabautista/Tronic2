package tronic2.useroperations

import java.awt.Color
import javax.swing.JOptionPane

class ChangeQuestions2Controller {
    // these will be injected by Griffon
    def model
    def view
    def usuarioDBService

    void mvcGroupInit(Map args) {
        //TODO cambiar
        Set preguntas = usuarioDBService.getPreguntas(app.models.principalScreen.usuario)
        edt {model.preguntas.addAll(preguntas)}
    }

    def salir = {
        destroyMVCGroup('changeQuestions2')
    }

    def botones = { evt = null ->

        if (evt.getActionCommand().equals('Salir')) {
            destroyMVCGroup('changeQuestions2')
            destroyMVCGroup('changeQuestions')
            app.views.usuarios.view.preguntasLabel.setForeground(Color.black)
        } else if (evt.getActionCommand().equals('Cambiar')) {
            String pregunta = (String) view.preguntasCombo.getSelectedItem()
            String respuesta = view.preguntasText.getText()
            usuarioDBService.cambiarRespuesta(pregunta, respuesta)
            view.questionsScreen2.getRootPane().setDefaultButton(view.cancelarButton)
            view.preguntasText.setEnabled(true)
            view.cancelarButton.setEnabled(true)
            view.eliminarButton.setEnabled(false)
            view.preguntasText.requestFocusInWindow()
        } else if (evt.getActionCommand().equals('...')) {
            //TODO cambiar
            def mvc = buildMVCGroup('changeQuestions3', [user: app.models.principalScreen.usuario])
            def pantalla = mvc.view.questionsScreen3
            pantalla.show()
        } else if (evt.getActionCommand().equals('Guardar')) {
            doOutside {
                String pregunta = (String) view.preguntasCombo.getSelectedItem()
                String respuesta = view.preguntasText.getText()
                usuarioDBService.cambiarRespuesta(pregunta, respuesta)
                model.preguntas.clear()
                //TODO cambiar
                preguntas = usuarioDBService.getPreguntas(app.models.principalScreen.usuario)
                model.respuestaOriginal = ""
                model.preguntas.addAll(preguntas)
                view.preguntasText.setEnabled(false)
                view.preguntasText.setText('')
                view.preguntasCombo.setSelectedItem(null)
                view.preguntasCombo.requestFocusInWindow()
                view.cancelarButton.setEnabled(false)
                model.habilitar = false
            }
        } else if (evt.getActionCommand().equals('Cancelar')) {
            view.preguntasText.setEnabled(false)
            view.preguntasText.setText('')
            view.preguntasCombo.setSelectedItem(null)
            model.textoCambia = false
            model.habilitar = false
            view.preguntasCombo.requestFocusInWindow()
            view.cancelarButton.setEnabled(false)
        } else if (evt.getActionCommand().equals('Eliminar')) {
            int answer = JOptionPane.showConfirmDialog(view.questionsScreen2, "Desea Eliminar la pregunta?", 'aviso', JOptionPane.YES_NO_OPTION)
            if (answer == JOptionPane.YES_OPTION) {
                Set preguntas
                doOutside {
                    String pregunta = (String) view.preguntasCombo.getSelectedItem()
                    usuarioDBService.eliminarPregunta(pregunta)
                    model.preguntas.clear()
                    //TODO cambiar
                    preguntas = usuarioDBService.getPreguntas(app.models.principalScreen.usuario)
                    model.respuestaOriginal = ""
                    view.cancelarButton.setEnabled(false)
                    model.preguntas.addAll(preguntas)
                    view.preguntasText.setEnabled(false)
                    view.preguntasText.setText('')
                    view.preguntasCombo.setSelectedItem(null)
                    view.preguntasCombo.requestFocusInWindow()
                    view.cancelarButton.setEnabled(false)
                    model.habilitar = false
                }
            }
        }


    }
    def cambioCombo = {
        edt {
            String pregunta = (String) view.preguntasCombo.getSelectedItem()
            model.respuestaOriginal = usuarioDBService.getRespuesta(pregunta)
            view.cancelarButton.setEnabled(true)
            view.preguntasText.setText(model.respuestaOriginal)
            view.questionsScreen2.getRootPane().setDefaultButton(view.cambiarButton)
            if (!pregunta.equals("")) {
                model.habilitar = true
            }
        }
    }

    def foco3 = { evt = null ->
        doLater {
            if (evt.getComponent().getName().equals('preguntasCombo')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.salirButton)
            } else if (evt.getComponent().getName().equals('newPreguntasButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.newPreguntasButton)
            } else if (evt.getComponent().getName().equals('eliminarButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.eliminarButton)
            } else if (evt.getComponent().getName().equals('cambiarButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.cambiarButton)
            } else if (evt.getComponent().getName().equals('guardarButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.guardarButton)
            } else if (evt.getComponent().getName().equals('salirButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.salirButton)
            } else if (evt.getComponent().getName().equals('cancelarButton')) {
                view.questionsScreen2.getRootPane().setDefaultButton(view.cancelarButton)
            }
        }
    }
}
