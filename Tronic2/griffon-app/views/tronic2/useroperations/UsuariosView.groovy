package tronic2.useroperations

import net.miginfocom.swing.MigLayout

dialog(name: 'usuariosScreen', id: 'usuariosScreen', title: 'Usuarios', pack: true, modal: true, owner: app.views.firstScreen,
        preferredSize: [250, 201], resizable: false, windowClosing: controller.salir) {
    panel(layout: new MigLayout('insets 10 10 0 0', '[][][]', '[]15[]')) {
        label(name: 'cuentaLabel', id: 'cuentaLabel', text: 'Administrador', font: ['Tahoma', 1, 18], constraints: 'wrap', mouseClicked: controller.labelAction)
        label(name: 'cuentaTipoLabel', id: 'cuentaTipoLabel', text: 'Tipo de cuenta', constraints: 'wrap', mouseClicked: controller.labelAction)
        label(name: 'passwordLabel', id: 'passwordLabel', text: 'Cambiar password', constraints: 'wrap', mouseClicked: controller.labelAction)
        label(name: 'preguntasLabel', id: 'preguntasLabel', text: 'Edicion de preguntas', constraints: 'wrap', mouseClicked: controller.labelAction)
        button(name: 'boton', id: 'boton', text: 'Salir', mnemonic: 'S', actionPerformed: controller.salir, constraints: 'gapleft 175')
    }
}
usuariosScreen.setLocationRelativeTo(null)
usuariosScreen.getRootPane().setDefaultButton(boton)
