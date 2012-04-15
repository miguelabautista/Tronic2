package tronic2.useroperations

import ca.odell.glazedlists.swing.EventComboBoxModel
import net.miginfocom.swing.MigLayout

dialog(name: 'questionsScreen2', id: 'questionsScreen2', pack: true, modal: true, owner: app.views.usuariosScreen,
        preferredSize: [390, 148], resizable: false, windowClosing: controller.salir) {
    panel(layout: new MigLayout('insets 10 10 0 0', '[][][]', '[]10[]')) {
        comboBox(name: 'preguntasCombo', id: 'preguntasCombo', preferredSize: [400, 23], model: new EventComboBoxModel<>(model.preguntas), itemStateChanged: controller.cambioCombo, focusGained: controller.foco3)
        button(name: 'newPreguntasButton', id: 'newPreguntasButton', text: '...', preferredSize: [23, 23], constraints: 'wrap', actionPerformed: controller.botones, focusGained: controller.foco3)
        textField(name: 'preguntasText', id: 'preguntasText', preferredSize: [400, 23], enabled: false, constraints: 'wrap', focusGained: controller.foco3)
        button(name: 'eliminarButton', id: 'eliminarButton', text: 'Eliminar', mnemonic: 'E', enabled: bind {model.habilitar && preguntasCombo.getSelectedIndex() != -1}, preferredSize: [71, 23], constraints: 'span 5, split 5', actionPerformed: controller.botones, focusGained: controller.foco3)
        button(name: 'cambiarButton', id: 'cambiarButton', text: 'Cambiar', mnemonic: 'C', enabled: bind {model.habilitar && preguntasText.enabled == false}, preferredSize: [71, 23], actionPerformed: controller.botones, focusGained: controller.foco3)
        button(name: 'guardarButton', id: 'guardarButton', text: 'Guardar', mnemonic: 'G', enabled: bind {model.textoCambia}, preferredSize: [71, 23], actionPerformed: controller.botones, focusGained: controller.foco3)
        button(name: 'cancelarButton', id: 'cancelarButton', text: 'Cancelar', enabled: false, mnemonic: 'A', preferredSize: [71, 23], actionPerformed: controller.botones, focusGained: controller.foco3)
        button(name: 'salirButton', id: 'salirButton', text: 'Salir', mnemonic: 'S', preferredSize: [71, 23], actionPerformed: controller.botones, focusGained: controller.foco3)
    }
}
bean(model, textoCambia: bind {preguntasText.text != model.respuestaOriginal})
questionsScreen2.setLocationRelativeTo(null)
