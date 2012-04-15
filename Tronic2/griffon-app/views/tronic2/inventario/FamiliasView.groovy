package tronic2.inventario

import net.java.balloontip.BalloonTip
import net.java.balloontip.styles.EdgedBalloonStyle
import net.miginfocom.swing.MigLayout

dialog(name: 'nuevoNombreScreen', id: 'nuevoNombreScreen', pack: true, modal: true, owner: app.views.administracioScreen,
        preferredSize: [289, 144], resizable: false,
        windowClosing: controller.salir) {
    panel(layout: new MigLayout('', '[][][]', '[]18[]')) {
        hudLabel(text: 'ingrese nueva familia', constraints: 'gapleft 20, wrap')
        hudTextField(name: 'familiaText', id: 'familiaText', preferredSize: [180, 23], constraints: ' gapleft 35, wrap', focusGained: controller.foco)
        balloonTip(id: 'ba1', text: 'campo no puede estar vacio', component: familiaText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        button(name: 'aceptarFamiliaButton', id: 'aceptarFamiliaButton', text: 'Aceptar', mnemonic: 'A', constraints: 'span,gapleft 95, align right,split 2', focusGained: controller.foco, actionPerformed: controller.crearFamilia)
        button(name: 'cancelarFamiliaButton', id: 'cancelarFamiliaButton', text: 'Cancelar', mnemonic: 'C', focusGained: controller.foco, actionPerformed: controller.salir)
    }
}
nuevoNombreScreen.setLocationRelativeTo(null)
nuevoNombreScreen.getRootPane().setDefaultButton(aceptarFamiliaButton)
