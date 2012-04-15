package tronic2.useroperations

import ca.odell.glazedlists.swing.EventComboBoxModel
import net.miginfocom.swing.MigLayout

dialog(name: 'obtainingScreen2', id: 'obtainingScreen2', title: 'Obtener clave', preferredSize: [293, 170], owner: app.views.obtainingScreen,
        pack: true, modal: true, resizable: false, windowClosing: controller.salir) {
    panel(layout: new MigLayout()) {
        label(text: 'Seleccione la pregunta', constraints: 'wrap 20')
        comboBox(name: 'preguntasCombo', selectedItem: null, model: new EventComboBoxModel<>(model.preguntas), id: 'preguntasCombo', preferredSize: [200, 23], constraints: 'gapleft 34, wrap 4', focusGained: controller.foco2)
        passwordField(name: 'preguntasText', id: 'preguntasText', preferredSize: [200, 23], constraints: 'gapleft 34, wrap 9')
        button(name: 'aceptarBotonPregunta', id: 'aceptarBotonPregunta', text: 'Aceptar', mnemonic: 'A', preferredSize: [71, 23], actionPerformed: controller.aceptarPregunta, constraints: 'gapleft 121,span 2, split2', focusGained: controller.foco2)
        button(name: 'salirBotonPregunta', id: 'salirBotonPregunta', text: 'Salir', mnemonic: 'S', preferredSize: [71, 23], actionPerformed: controller.salir, focusGained: controller.foco2)
    }
}
obtainingScreen2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE)
obtainingScreen2.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL)
obtainingScreen2.setLocationRelativeTo(null)
obtainingScreen2.getRootPane().setDefaultButton(aceptarBotonPregunta)