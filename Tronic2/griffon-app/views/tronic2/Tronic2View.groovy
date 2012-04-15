package tronic2

import ca.odell.glazedlists.swing.EventComboBoxModel
import net.java.balloontip.BalloonTip
import net.java.balloontip.styles.EdgedBalloonStyle
import net.miginfocom.swing.MigLayout

application(name: 'bienvenidoScreen', id: 'bienvenidoScreen', title: 'BIENVENIDO',
        //TODO:cambio
        preferredSize: [420, 328], componentResized: controller.tamano,
        pack: true, resizable: false,
        locationByPlatform: true,
        iconImage: imageIcon('/LOGO.png').image) {
    panel(layout: new MigLayout('insets 6 5 0 0')) {
        hudLabel(icon: imageIcon('/LOGO sin fondo.png'), constraints: 'wrap')
        hudLabel(text: 'BIENVENIDO', font: ['Timer New Roman', 1, 36], foreground: Color.red, constraints: 'gapleft 85,wrap')
        hudLabel(text: 'Ingrese su password', constraints: 'gapleft 135, wrap')
        comboBox(name: 'passwordCombo', id: 'passwordCombo', model: new EventComboBoxModel<>(model.usuario), selectedItem: null, preferredSize: [148, 23], constraints: 'gapleft 123, wrap', focusGained: controller.foco, itemStateChanged: controller.comboItem)
        passwordField(name: 'passwordText', id: 'passwordText', horizontalAlignment: JTextField.CENTER, preferredSize: [148, 23], enabled: false, constraints: 'gapleft 123, wrap', focusGained: controller.foco, keyPressed: controller.capsLock)
        balloonTip(id: 'ba1', text: 'Mayuscula', component: passwordText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudButton(name: 'ingresarButton', id: 'ingresarButton', text: 'Ingresar', enabled: bind {passwordText.enabled == true}, mnemonic: 'I', preferredSize: [71, 23], constraints: 'gapleft 123, split2', actionPerformed: controller.entrar, focusGained: controller.foco)
        hudButton(name: 'salirButton', id: 'salirButton', text: 'Salir', mnemonic: 'S', preferredSize: [71, 23], constraints: 'wrap 23', actionPerformed: controller.salir, focusGained: controller.foco)
        hudLabel(text: 'Olvido su password?', constraints: 'split 2')
        hudButton(name: 'ingresarPasswordButton', id: 'ingresarPasswordButton', text: 'Ingresar', mnemonic: 'N', preferredSize: [71, 23], constraints: 'align left', focusGained: controller.foco, actionPerformed: controller.obtenerClave)
    }
}
bienvenidoScreen.setLocationRelativeTo(null)
bienvenidoScreen.rootPane.setDefaultButton(salirButton)