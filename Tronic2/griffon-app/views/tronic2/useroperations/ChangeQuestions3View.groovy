package tronic2.useroperations

import net.miginfocom.swing.MigLayout

dialog(name: 'questionsScreen3', id: 'questionsScreen3', title: 'Nueva pregunta', pack: true,
        modal: true, owner: app.views.questionsScreen2, preferredSize: [336, 148], resizable: false, windowClosing: controller.salir) {
    panel(layout: new MigLayout('insets 10 10 0 0', '[][][]', '[]10[]')) {
        label(text: 'Pregunta', constraints: 'align right')
        textField(name: 'nuevaPreguntaText', id: 'nuevaPreguntaText', preferredSize: [400, 23], text: bind(target: model, 'nuevaPregunta'), focusGained: controller.foco4, constraints: 'wrap', keyTyped: controller.textChanged)
        balloonTip(id: 'tip1', text: 'no puede estar vacio', component: nuevaPreguntaText)
        label(text: 'Respuesta', constraints: 'align right')
        textField(name: 'nuevaRespuestaText', id: 'nuevaRespuestaText', preferredSize: [400, 23], text: bind(target: model, 'nuevaRespuesta'), constraints: 'wrap', keyTyped: controller.textChanged2)
        balloonTip(id: 'tip2', text: 'no puede estar vacio', component: nuevaRespuestaText)
        button(name: 'crearButton', id: 'crearButton', text: 'Aceptar', mnemonic: 'A', preferredSize: [71, 23], constraints: 'span 2, split 2, align right', actionPerformed: controller.crearPregunta, focusGained: controller.foco4)
        button(name: 'cancelButton', id: 'cancelButton', text: 'Cancelar', mnemonic: 'C', preferredSize: [71, 23], actionPerformed: controller.salir, focusGained: controller.foco4)
    }
}
questionsScreen3.setLocationRelativeTo(null)
questionsScreen3.getRootPane().setDefaultButton(crearButton)
