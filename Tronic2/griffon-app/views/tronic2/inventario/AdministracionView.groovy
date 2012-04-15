package tronic2.inventario

import ca.odell.glazedlists.FilterList
import ca.odell.glazedlists.swing.TextComponentMatcherEditor
import net.java.balloontip.BalloonTip
import net.java.balloontip.styles.EdgedBalloonStyle
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator

dialog(name: 'administracionScreen', id: 'administracionScreen', title: 'Administracion', modal: true, owner: app.views.firstScreen,
        preferredSize: [744, 493], resizable: true, pack: true,
        windowClosing: controller.salir) {
    panel(layout: new MigLayout('', '[][][]', '[]15[]')) {
        hudLabel(text: 'seleccione tipo de busqueda', constraints: 'align right')
        hudComboBox(name: 'busquedaCombo', id: 'busquedaCombo', items: model.tipoBusqueda, focusGained: controller.foco, constraints: 'split 2', itemStateChanged: controller.busquedaCombo)
        jxtextField(name: 'busquedaText', id: 'busquedaText', preferredSize: [125, 23], focusGained: controller.foco, keyPressed: controller.teclas) {
            AutoCompleteDecorator.decorate(busquedaText, model.busquedaArticulos, true)
        }
        button(name: 'borrarBoton', id: 'borrarBoton', text: 'Borrar', mnemonic: 'B', actionPerformed: controller.borrarButton, focusGained: controller.foco, constraints: 'wrap')
        scrollPane(constraints: 'span, growx, wrap') {
            table(name: 'articulosTable', id: 'articulosTable', keyPressed: controller.teclas, focusGained: controller.foco, focusLost: controller.focoPerdido) {
                articulosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
                TextComponentMatcherEditor matcherEditor = new TextComponentMatcherEditor(busquedaText, model.filterator)
                FilterList filterList = new FilterList(model.articulos, matcherEditor)
                tableFormat = defaultTableFormat(columnNames: ['Codigo', 'Nombre', 'Descripcion', 'Stock', 'Precio Unitario', 'Precio Sin Iva', 'Pvp', 'Precio Lotes', 'Nte', 'Factura', 'Adicional', 'n_Precio'])
                eventTableModel(source: filterList, format: tableFormat)
                installTableComparatorChooser(source: model.articulos)
            }
        }
        hudLabel(text: 'Codigo', constraints: 'span 8, split 8,gapleft 4, align right')
        textField(name: 'codigoText', id: 'codigoText', preferredSize: [125, 23], text: bind {model.codigo}, enabled: false)
        hudLabel(text: 'Nombre', constraints: 'align right')
        jxtextField(name: 'nombreText', id: 'nombreText', preferredSize: [125, 23], text: bind {model.nombre}, errorRenderer: 'for:nombreNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable}, keyPressed: controller.teclas) {
            AutoCompleteDecorator.decorate(nombreText, model.busquedaFamilias, true)
        }
        balloonTip(id: 'ba1', text: 'No puede estar vacio', component: nombreText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Descripcion', constraints: 'align right')
        textField(name: 'descripcionText', id: 'descripcionText', preferredSize: [125, 23], text: bind(source: model, sourceProperty: 'descripcion'), errorRenderer: 'for:descripcionNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        balloonTip(id: 'ba2', text: 'no puede estar vacio', component: descripcionText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Stock', constraints: 'align right')
        textField(name: 'stockText', id: 'stockText', preferredSize: [125, 23], constraints: 'wrap', text: bind {model.stock}, errorRenderer: 'for:stockNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        balloonTip(id: 'ba3', text: 'Debe ser un valor numerico valido', component: stockText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'P/U', constraints: 'span 8, split 8, align right')
        textField(name: 'puText', id: 'puText', preferredSize: [125, 23], text: bind {model.precioUnitario}, errorRenderer: 'for:precioUnitarioNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        balloonTip(id: 'ba4', text: 'Debe ser un valor numerico valido', component: puText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'P/sinIva', constraints: 'align right')
        textField(name: 'psiText', id: 'psiText', preferredSize: [125, 23], text: bind {model.precioSinIva}, errorRenderer: 'for:precioSinIvaNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable}, focusGained: controller.foco, focusLost: controller.focoPerdido)
        balloonTip(id: 'ba5', text: 'Debe ser un valor numerico valido', component: psiText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'PVP', constraints: 'align right')
        textField(name: 'pvpText', id: 'pvpText', preferredSize: [125, 23], text: bind {model.pvp}, errorRenderer: 'for:pvpNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        balloonTip(id: 'ba6', text: 'Debe ser un valor numerico valido', component: pvpText, alignment: BalloonTip.Orientation.RIGHT_ABOVE, style: new EdgedBalloonStyle(Color.white, Color.blue), attachLocation: BalloonTip.AttachLocation.NORTHEAST, horizontalOffset: 40, verticalOffset: 15, useCloseButton: false)
        hudLabel(text: 'Precio Lotes', constraints: 'align right')
        textField(name: 'plotesText', id: 'plotesText', preferredSize: [125, 23], constraints: 'wrap', errorRenderer: 'for:precioLotesNuevo,styles:[hightlight]', text: bind {model.precioLotes}, enabled: false)
        hudLabel(text: 'NTE', constraints: 'span 8, split 8, align right')
        textField(name: 'nteText', id: 'nteText', preferredSize: [125, 23], text: bind {model.nte}, errorRenderer: 'for:nteNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        hudLabel(text: 'Adicional', constraints: 'align right')
        textField(name: 'adicionalText', id: 'adicionalText', preferredSize: [125, 23], text: bind {model.adicional}, errorRenderer: 'for:adicionalNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        hudLabel(text: 'NPrecio', constraints: 'align right')
        textField(name: 'nprecioText', id: 'nprecioText', preferredSize: [125, 23], text: bind {model.n_precio}, errorRenderer: 'for:n_precioNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        hudLabel(text: 'Factura', constraints: 'align right')
        textField(name: 'facturaText', id: 'facturaText', preferredSize: [125, 23], constraints: 'wrap', text: bind {model.factura}, errorRenderer: 'for:facturaNuevo,styles:[hightlight]', enabled: bind {model.textFieldsEnable})
        hudButton(name: 'familiasButton', id: 'familiasButton', text: 'Familias', mnemonic: 'F', focusGained: controller.foco, actionPerformed: controller.actionButtons)
        button(name: 'newButton', id: 'newButton', text: 'Nuevo', mnemonic: 'N', focusGained: controller.foco, constraints: 'span 7, split 7', actionPerformed: controller.actionButtons, enabled: bind {model.nuevo})
        button(name: 'modificarButton', id: 'modificarButton', text: 'Modificar', mnemonic: 'M', focusGained: controller.foco, actionPerformed: controller.actionButtons, enabled: bind {model.modificar})
        button(name: 'cancelarButton', id: 'cancelarButton', text: 'Cancelar', mnemonic: 'C', focusGained: controller.foco, actionPerformed: controller.actionButtons, enabled: bind {model.cancelar})
        button(name: 'guardarButton', id: 'guardarButton', text: 'Guardar', mnemonic: 'G', focusGained: controller.foco, actionPerformed: controller.actionButtons, enabled: bind {model.guardar})
        button(name: 'eliminarButton', id: 'eliminarButton', text: 'Eliminar', mnemonic: 'E', focusGained: controller.foco, actionPerformed: controller.actionButtons, enabled: bind {model.eliminar})
        button(name: 'actualizarButton', id: 'actualizarButton', text: 'Actualizar', mnemonic: 'A', focusGained: controller.foco, actionPerformed: controller.actionButtons)
        button(name: 'salirButton', id: 'salirButton', text: 'Salir', mnemonic: 'S', focusGained: controller.foco, actionPerformed: controller.salir)
    }
}
bean(model, nombreNuevo: bind(source: nombreText, sourceProperty: 'text'))
bean(model, descripcionNuevo: bind {descripcionText.text})
bean(model, stockNuevo: bind(source: stockText, sourceProperty: 'text', validator: model.isNumber))
bean(model, precioUnitarioNuevo: bind(source: puText, sourceProperty: 'text', validator: model.isNumber))
bean(model, precioSinIvaNuevo: bind(source: psiText, sourceProperty: 'text', validator: model.isNumber))
bean(model, pvpNuevo: bind(source: pvpText, sourceProperty: 'text', validator: model.isNumber))
bean(model, precioLotesNuevo: bind {plotesText.text})
bean(model, nteNuevo: bind {nteText.text})
bean(model, adicionalNuevo: bind {adicionalText.text})
bean(model, n_precioNuevo: bind {nprecioText.text})
bean(model, facturaNuevo: bind {facturaText.text})
articulosTable.getTableHeader().setReorderingAllowed(false)
//TODO: comprobar esta funcionalidad
bean(model, selection: bind {busquedaCombo.getSelectedItem().equals("Familia")})
administracionScreen.setLocationRelativeTo(null)
busquedaText.requestFocusInWindow()


