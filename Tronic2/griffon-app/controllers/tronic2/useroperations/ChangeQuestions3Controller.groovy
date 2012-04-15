package tronic2.useroperations

class ChangeQuestions3Controller {
    // these will be injected by Griffon
    def model
    def view
    def usuarioDBService

    void mvcGroupInit(Map args) {
        model.usuario = args.user
    }

    def salir = {
        destroyMVCGroup('changeQuestions3')
    }

    def foco4 = { evt = null ->

        if (evt.getComponent().getName().equals('nuevaPreguntaText')) {
            view.questionsScreen3.getRootPane().setDefaultButton(view.crearButton)
        } else if (evt.getComponent().getName().equals('crearButton')) {
            view.questionsScreen3.getRootPane().setDefaultButton(view.crearButton)
        } else if (evt.getComponent().getName().equals('cancelButton')) {
            view.questionsScreen3.getRootPane().setDefaultButton(view.cancelButton)
        }
    }
    //TODO error en validationes
    def crearPregunta = { evt = null ->

        if (model.nuevaPregunta.equals('')) {
            view.tip1.setVisible(true)
            view.nuevaPreguntaText.requestFocusInWindow()
        } else if (model.nuevaRespuesta.equals('')) {
            view.tip2.setVisible(true)
            view.nuevaRespuestaText.requestFocusInWindow()
        } else {
            doOutside {
                usuarioDBService.crearPregunta(model.nuevaPregunta, model.nuevaRespuesta, model.usuario)
                app.models.changeQuestions2.preguntas.clear()
                def preguntas = usuarioDBService.getPreguntas("ADMINISTRADOR")
                edt {app.models.changeQuestions2.preguntas.addAll(preguntas)}
                destroyMVCGroup('changeQuestions3')
            }
        }
    }

    def textChanged = { evt = null ->
        if (model.nuevaPregunta != '') {
            view.tip1.setVisible(false)
        } else {
            view.tip1.setVisible(true)
        }
    }
    def textChanged2 = { evt = null ->
        if (model.nuevaRespuesta != '') {
            view.tip2.setVisible(false)
        } else {
            view.tip2.setVisible(true)
        }
    }

}
