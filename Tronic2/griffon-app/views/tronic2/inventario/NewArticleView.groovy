package tronic2.inventario

import ca.odell.glazedlists.swing.EventComboBoxModel
import net.java.balloontip.BalloonTip
import net.java.balloontip.styles.EdgedBalloonStyle
import net.miginfocom.swing.MigLayout

dialog(name: 'newArticleScreen', id: 'newArticleScreen', title: 'Ingresar nuevo articulo', owner: model.own, modal: true,
        preferredSize: [388, 290], pack: true, resizable: false,
        windowClosing: controller.salir) {
    panel(layout: new MigLayout('', '[][][]', '[]10[]')) {
        hudLabel(text: 'Codigo', constraints: 'align right')
        hudTextField(name: 'codigoText', id: 'codigoText', preferredSize: [100, 23], constraints: 'split 2', focusGained: controller.foco, focusLost: controller.focoLost)
        balloonTip(id: 'ba1', text: 'Mayuscula', component: codigoText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Nombre', constraints: 'gapleft 15, align right')
        comboBox(name: 'nombreCombo', id: 'nombreCombo', preferredSize: [125, 23], selectedIndex: -1, model: new EventComboBoxModel<>(model.articulos), itemStateChanged: controller.articulosCambio, constraints: 'wrap')
        balloonTip(id: 'ba2', text: 'Mayuscula', component: nombreCombo, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Descripcion', constraints: 'align right')
        hudTextField(name: 'descripcionText', id: 'descripcionText', constraints: 'span,height 23, growx, wrap')
        balloonTip(id: 'ba3', text: 'Mayuscula', component: descripcionText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Stock', constraints: 'align right')
        comboBox(name: 'stockCombo', id: 'stockCombo', preferredSize: [50, 23], items: model.items, constraints: 'split 2')
        balloonTip(id: 'ba7', text: 'Mayuscula', component: stockCombo, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Precio Unitario', constraints: 'gapleft 10, align right')
        hudTextField(name: 'puText', id: 'puText', preferredSize: [145, 23], constraints: 'wrap')
        balloonTip(id: 'ba4', text: 'Mayuscula', component: puText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Precio sin IVA', constraints: 'align right')
        hudTextField(name: 'psText', id: 'psText', preferredSize: [125, 23], constraints: 'split 2', focusGained: controller.foco, focusLost: controller.focoLost)
        balloonTip(id: 'ba5', text: 'Mayuscula', component: psText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'PVP', constraints: 'gapleft 10, align right')
        hudTextField(name: 'pvpText', id: 'pvpText', preferredSize: [125, 23], constraints: 'wrap')
        balloonTip(id: 'ba6', text: 'Mayuscula', component: pvpText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.WHITE, Color.BLUE), attachLocation: BalloonTip.AttachLocation.NORTHWEST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel('Opcional', constraints: 'align right')
        separator(constraints: 'span ,growx, wrap')
        hudLabel(text: 'NTE', constraints: 'align right')
        hudTextField(name: 'nteText', id: 'nteText', preferredSize: [125, 23], constraints: 'split 2')
        hudLabel(text: 'Adicional', constraints: 'gapleft 10, align right')
        hudTextField(name: 'adicionalText', id: 'adicionalText', preferredSize: [125, 23], constraints: 'wrap')
        hudLabel(text: 'N/Precio', constraints: 'align right')
        hudTextField(name: 'nPrecioText', id: 'nPrecioText', preferredSize: [125, 23], constraints: 'split 2')
        hudLabel(text: 'Factura', constraints: 'gapleft 10, align right')
        hudTextField(name: 'facturaText', id: 'facturaText', preferredSize: [125, 23], constraints: 'wrap')

        hudButton(name: 'aceptarBoton', id: 'aceptarBoton', text: 'Aceptar', preferredSize: [71, 23], mnemonic: 'A', constraints: 'span , align right, split 2', focusGained: controller.foco, actionPerformed: controller.crearArticulo)
        hudButton(name: 'salirBoton', id: 'salirBoton', text: 'Cancelar', preferredSize: [71, 23], mnemonic: 'C', actionPerformed: controller.salir, focusGained: controller.foco)
    }
}
dialog(name: 'nuevoNombreScreen', id: 'nuevoNombreScreen', pack: true, modal: true, owner: newArticleScreen,
        preferredSize: [289, 144], resizable: false,
        windowClosing: controller.cancel) {
    panel(layout: new MigLayout('', '[][][]', '[]18[]')) {
        hudLabel(text: 'ingrese nueva familia', constraints: 'gapleft 20, wrap')
        hudTextField(name: 'familiaText', id: 'familiaText', preferredSize: [180, 23], constraints: ' gapleft 35, wrap', focusGained: controller.foco)
        button(name: 'aceptarFamiliaButton', id: 'aceptarFamiliaButton', text: 'Aceptar', mnemonic: 'A', constraints: 'span,gapleft 95, align right,split 2', focusGained: controller.foco, actionPerformed: controller.crearFamilia)
        button(name: 'cancelarFamiliaButton', id: 'cancelarFamiliaButton', text: 'Cancelar', mnemonic: 'C', focusGained: controller.foco, actionPerformed: controller.cancel)
    }
}
newArticleScreen.getRootPane().setDefaultButton(aceptarBoton)
newArticleScreen.setLocationRelativeTo(null)
