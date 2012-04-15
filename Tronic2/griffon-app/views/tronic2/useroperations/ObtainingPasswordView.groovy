package tronic2.useroperations

import ca.odell.glazedlists.swing.EventComboBoxModel
import net.miginfocom.swing.MigLayout

dialog(id: 'obtainingScreen', name: 'obtainingScreen', title: 'Seleccione el usuario', pack: true,
        preferredSize: [293, 148], owner: app.views.bienvenidoScreen, modal: true, resizable: false, windowClosing: controller.salir) {
    panel(layout: new MigLayout()) {
        label(text: 'Seleccione el usuario', constraints: 'wrap 20')
        comboBox(name: 'usuarioCombo', id: 'usuarioCombo', preferredSize: [200, 23], model: new EventComboBoxModel<>(model.usuarios), selectedItem: null, constraints: 'gapleft 34, wrap 14', focusGained: controller.foco1)
        button(name: 'aceptarBoton', id: 'aceptarBoton', text: 'Aceptar', mnemonic: 'A', preferredSize: [71, 23], constraints: 'gapleft 121, span 2, split 2', focusGained: controller.foco1, actionPerformed: controller.aceptar)
        button(name: 'salirBoton', id: 'salirBoton', text: 'Salir', mnemonic: 'S', preferredSize: [71, 23], focusGained: controller.foco1, actionPerformed: controller.salir)
    }
}
obtainingScreen.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE)
obtainingScreen.setModalityType(Dialog.ModalityType.APPLICATION_MODAL)
obtainingScreen.setLocationRelativeTo(null)
obtainingScreen.getRootPane().setDefaultButton(aceptarBoton)
