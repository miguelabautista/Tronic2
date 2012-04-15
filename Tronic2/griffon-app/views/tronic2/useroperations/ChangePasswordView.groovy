package tronic2.useroperations

import net.java.balloontip.BalloonTip
import net.java.balloontip.styles.EdgedBalloonStyle
import net.miginfocom.swing.MigLayout

dialog(name: 'passwordScreen', id: 'passwordScreen', title: 'cambiar password', modal: true, owner: app.views.usuariosScreen, pack: true,
        preferredSize: [422, 190], windowClosing: controller.salir, resizable: false) {
    panel(layout: new MigLayout('insets 10 10 0 0', '[][][]', '[]10[]')) {
        label(text: 'ingrese su password', constraints: 'align right')
        passwordField(name: 'passwordText', id: 'passwordText', preferredSize: [400, 23], text: bind(target: model, 'originalPassword'), keyPressed: controller.&capsLock, focusGained: controller.foc, focusLost: controller.focoPerdido, constraints: 'wrap', errorRenderer: 'for:originalPassword,styles:[highlight]')
        balloonTip(id: 'ba1', text: 'Mayuscula', component: passwordText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        label(text: 'Ingrese su nuevo password', constraints: 'align right')
        passwordField(name: 'passwordText2', id: 'passwordText2', preferredSize: [400, 23], text: bind(target: model, 'newPassword'), keyPressed: controller.&capsLock, focusGained: controller.foc, focusLost: controller.focoPerdido, constraints: 'wrap', errorRenderer: 'for:newPassword,styles:[highlight]')
        balloonTip(id: 'ba2', text: 'Mayuscula', component: passwordText2, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        label(text: 'repita su nuevo password', constraints: 'align right')
        passwordField(name: 'passwordText3', id: 'passwordText3', preferredSize: [400, 23], text: bind(target: model, 'repeatPassword'), keyPressed: controller.&capsLock, focusGained: controller.foc, focusLost: controller.focoPerdido, constraints: 'wrap', errorRenderer: 'for:repeatPassword,styles:[highlight]')
        balloonTip(id: 'ba3', text: 'Mayuscula', component: passwordText3, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        button(name: 'aceptarButton', id: 'aceptarButton', preferredSize: [71, 23], text: 'Aceptar', mnemonic: 'A', constraints: 'span 2, split 2, align right', actionPerformed: controller.cambiarClave, focusGained: controller.foc)
        button(name: 'salirButton', id: 'salirButton', preferredSize: [71, 23], text: 'Salir', mnemonic: 'S', actionPerformed: controller.salir, focusGained: controller.foc)
    }
}
passwordScreen.setLocationRelativeTo(null)
passwordScreen.getRootPane().setDefaultButton(aceptarButton)
