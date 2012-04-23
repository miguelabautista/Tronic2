package tronic2.inventario

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.KeyEvent
import javax.swing.JOptionPane
import javax.swing.Timer

class AdministracionController {
    def model
    def view
    def inventarioDBService
    Timer time, time2


    void mvcGroupInit(Map args) {
        campos()
        calculos()
        doOutside {
            def arts = inventarioDBService.getInventario()
            def familias = inventarioDBService.getNombreProductos()
            edt {model.articulos.addAll(arts); model.busquedaFamilias.addAll(familias)}
            for (a in arts) {
                model.busquedaArticulos.add(a.codigo)
            }
        }
    }

    def calculos() {
        time2 = new Timer(1000, { ActionEvent e ->
            double c = 0;
            int entero = 0;
            double fraccion = 0;
            try {
                double a = 0;
                a = model.precioSinIvaNuevo.toDouble()
                c = a + (a * 0.12);
                entero = (int) c;
                fraccion = c - entero;
            } catch (NumberFormatException ec) {
            }
            if (fraccion > 0.40 && fraccion <= 0.49) {
                double f = 0.5;
                view.pvpText.text = String.valueOf(entero + f)
            } else {
                view.pvpText.text = String.valueOf(Math.round(c))
            }
            double lotes
            try {
                lotes = Double.parseDouble(model.pvpNuevo) * Double.parseDouble(model.stockNuevo)
            } catch (NumberFormatException error) {}
            view.plotesText.text = String.valueOf((int) lotes)

        } as ActionListener)
    }

    def campos() {
        time = new Timer(1000, {ActionEvent evt = null ->
            edt {
                model.codigo = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 0)
                model.nombre = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 1)
                model.descripcion = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 2)
                model.stock = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 3)
                model.precioUnitario = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 4)
                model.precioSinIva = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 5)
                model.pvp = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 6)
                model.precioLotes = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 7)
                model.nte = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 8)
                model.factura = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 9)
                model.adicional = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 10)
                model.n_precio = view.articulosTable.getValueAt(view.articulosTable.getSelectedRow(), 11)
            }
            if (view.articulosTable.getSelectedRow() != -1) {
                model.eliminar = true
                model.modificar = true
            }
        } as ActionListener)
    }

    def salir = { evt = null ->
        time2.stop()
        destroyMVCGroup('administracion')
    }

    def foco = { evt = null ->
        if (evt.component.name.equals("busquedaText")) {

        } else if (evt.component.name.equals("busquedaCombo")) {

        } else if (evt.component.name.equals("familiasButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.familiasButton)
        } else if (evt.component.name.equals("newButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.newButton)
        } else if (evt.component.name.equals("modificarButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.modificarButton)
        } else if (evt.component.name.equals("cancelarButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.cancelarButton)
        } else if (evt.component.name.equals("guardarButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.guardarButton)
        } else if (evt.component.name.equals("eliminarButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.eliminarButton)
        } else if (evt.component.name.equals("actualizarButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.actualizarButton)
        } else if (evt.component.name.equals("salirButton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.salirButton)
        } else if (evt.component.name.equals("borrarBoton")) {
            view.administracionScreen.getRootPane().setDefaultButton(view.borrarBoton)
        } else if (evt.component.name.equals("articulosTable")) {
            time.start()
        } else if (evt.component.name.equals("psiText")) {
            time2.start()
        } else if (evt.component.name.equals("pvpText")) {
            time2.stop()
        }
    }
    def focoPerdido = { evt = null ->
        if (evt.component.name.equals("articulosTable")) {
            time.stop()
        } else if (evt.component.name.equals("psitext")) {
            time2.stop()
        }
    }
    def busquedaCombo = { ItemEvent evt = null ->
        if (view.busquedaCombo.getSelectedItem().equals('Codigo')) {
            model.busquedaArticulos.clear()
            view.busquedaText.setText('')
            for (ar in model.articulos) {
                model.busquedaArticulos.add(ar.codigo)
            }
        } else {
            model.busquedaArticulos.clear()
            view.busquedaText.setText('')
            for (ar in model.articulos) {
                model.busquedaArticulos.add(ar.nombre)
            }
        }
    }
    def teclas = { KeyEvent evt = null ->
        if (evt.component.name.equals('busquedaText') && evt.keyChar == KeyEvent.VK_DELETE) {
            view.busquedaText.setText('')
        } else if (evt.component.name.equals('busquedaText') && evt.keyChar == KeyEvent.VK_ENTER) {

        } else if (evt.component.name.equals('articulosTable') && evt.getKeyCode() == 9) {
            view.codigoText.requestFocusInWindow()
        } else if (evt.component.name.equals('nombreText') && evt.keyChar == KeyEvent.VK_DELETE) {
            view.nombreText.setText('')
        }
    }
    def borrarButton = {evt = null ->
        view.busquedaText.setText('')
    }

    def actionButtons = { ActionEvent evt = null ->
        if (evt.actionCommand.equals('Modificar')) {
            model.textFieldsEnable = true
            model.modificar = false
            model.cancelar = true
            model.guardar = true
        } else if (evt.actionCommand.equals('Nuevo')) {
            model.nuevo = false
            model.eliminar = false
            model.modificar = false
            view.articulosTable.clearSelection()
            def mvc = buildMVCGroup('newArticle', [own: view.administracionScreen])
            def pantalla = mvc.view.newArticleScreen
            pantalla.show()
        } else if (evt.actionCommand.equals('Cancelar')) {
            doLater {
                model.textFieldsEnable = false
                model.nuevo = true
                model.codigo = ""
                model.nombre = ''
                model.descripcion = ''
                model.stock = ''
                model.precioUnitario = ''
                model.precioSinIva = ''
                model.pvp = ''
                model.precioLotes = ''
                model.nte = ''
                model.factura = ''
                model.adicional = ''
                model.n_precio = ''
                model.cancelar = false
                model.guardar = false
                if (view.articulosTable.getSelectedRow() != -1) {
                    model.modificar = true
                }
                view.articulosTable.clearSelection()
            }
        } else if (evt.actionCommand.equals('Eliminar')) {
            int result = JOptionPane.showConfirmDialog(view.administracionScreen, "Desea eliminar el articulo?", "Aviso", JOptionPane.YES_NO_OPTION)
            if (result == JOptionPane.YES_OPTION) {
                doOutside {
                    inventarioDBService.deleteArticulo(model.codigo)
                    model.articulos.clear()
                    def art = inventarioDBService.getInventario()
                    edt {model.articulos.addAll(art)}
                }
            }
            model.eliminar = false
            model.modificar = false
        } else if (evt.actionCommand.equals('Guardar')) {
            if (!model.validate()) {
                if (model.errors.hasFieldErrors('precioSinIvaNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba5.text = 'no puede estar en blanco'
                        view.ba5.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba5.setVisible false
                    } else if (valor == 'default.matches.message') {
                        view.ba5.text = 'debe ser un valor numerico valido'
                        view.ba5.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba5.setVisible false
                    }
                    view.psiText.requestFocusInWindow()
                }
                if (model.errors.hasFieldErrors('pvpNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba6.text = 'no puede estar en blanco'
                        view.ba6.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba6.setVisible false
                    } else if (valor == 'default.matches.message') {
                        view.ba6.text = 'debe ser un valor numerico valido'
                        view.ba6.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba6.setVisible false
                    }
                    view.pvpText.requestFocusInWindow()
                }
                if (model.errors.hasFieldErrors('nombreNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba1.text = 'no puede estar en blanco'
                        view.ba1.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba1.setVisible false
                    }
                    view.nombreText.requestFocusInWindow()
                }
                if (model.errors.hasFieldErrors('descripcionNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba2.text = 'no puede estar en blanco'
                        view.ba2.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba2.setVisible false
                    }
                    view.descripcionText.requestFocusInWindow()
                }
                if (model.errors.hasFieldErrors('stockNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba3.text = 'no puede estar en blanco'
                        view.ba3.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba3.setVisible false
                    } else if (valor == 'default.matches.message') {
                        view.ba3.text = 'debe ser un valor numerico valido'
                        view.ba3.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba3.setVisible false
                    }
                    view.stockText.requestFocusInWindow()
                }
                if (model.errors.hasFieldErrors('precioUnitarioNuevo')) {
                    def valor
                    model.errors.each {
                        valor = it.defaultErrorCode
                    }
                    if (valor == 'default.blank.message') {
                        view.ba4.text = 'no puede estar en blanco'
                        view.ba4.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba4.setVisible false
                    } else if (valor == 'default.matches.message') {
                        view.ba4.text = 'debe ser un valor numerico valido'
                        view.ba4.setVisible true
                        doOutside {Thread.sleep(2000)}
                        view.ba4.setVisible false
                    }
                    view.puText.requestFocusInWindow()
                }
            } else {
                doOutside {
                    inventarioDBService.updateArticulo(model.nombreNuevo, model.codigo, model.precioUnitarioNuevo, model.stockNuevo, model.precioSinIvaNuevo
                            , model.pvpNuevo, model.descripcionNuevo, model.precioLotesNuevo, model.nteNuevo, model.adicionalNuevo, model.facturaNuevo, model.n_precioNuevo)
                    model.textFieldsEnable = false
                    model.codigo = ""
                    model.nombre = ''
                    model.descripcion = ''
                    model.stock = ''
                    model.precioUnitario = ''
                    model.precioSinIva = ''
                    model.pvp = ''
                    model.precioLotes = ''
                    model.nte = ''
                    model.factura = ''
                    model.adicional = ''
                    model.n_precio = ''
                    model.articulos.clear()
                    model.cancelar = false
                    model.eliminar = false
                    model.guardar = false
                    def art = inventarioDBService.getInventario()
                    edt {model.articulos.addAll(art)}
                }
            }
        } else if (evt.actionCommand.equals('Actualizar')) {
            doOutside {
                model.articulos.clear()
                def art = inventarioDBService.getInventario()
                edt {model.articulos.addAll(art)}
            }

        } else if (evt.actionCommand.equals('Familias')) {
            def mvc = buildMVCGroup('familias')
            def pantalla = mvc.view.nuevoNombreScreen
            pantalla.show()
        }
    }

    def onVentanaArticuloCerrada = {
        model.nuevo = true
        doOutside {
            model.articulos.clear()
            def art = inventarioDBService.getInventario()
            edt {model.articulos.addAll(art)}
        }
    }

    def onFamilia = {
        model.busquedaFamilias.clear()
        def familias = inventarioDBService.getNombreProductos()
        edt { model.busquedaFamilias.addAll(familias) }
    }


}
