package tronic2.useroperations

import net.miginfocom.swing.MigLayout

dialog(name: 'questionsScreen', id: 'questionsScreen', title: 'Preguntas', preferredSize: [293, 148], pack: true,
        windowClosing: controller.salir, resizable: false, modal: true, owner: app.views.usuariosScreen) {
    panel(layout: new MigLayout('insets 10 10 0 0')) {
        label(text: 'Ingrese su clave', constraints: 'wrap 20')
        passwordField(name: 'claveField', id: 'claveField', preferredSize: [200, 23], constraints: 'gapleft 34, wrap 14', focusGained: controller.foco2)
        button(name: 'aceptarButton', id: 'aceptarButton', text: 'Aceptar', mnemonic: 'A', preferredSize: [71, 23], constraints: 'gapleft 121,span 2, split 2', actionPerformed: controller.aceptar, focusGained: controller.foco2)
        button(name: 'cancelarButton', id: 'cancelarButton', text: 'Cancelar', mnemonic: 'C', preferredSize: [71, 23], actionPerformed: controller.salir, focusGained: controller.foco2)
    }
}
questionsScreen.setLocationRelativeTo(null)
questionsScreen.getRootPane().setDefaultButton(aceptarButton)
