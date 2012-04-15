package tronic2.inventario

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import javax.swing.Timer

class NewArticleController {
    // these will be injected by Griffon
    def model
    def view
    def inventarioDBService
    Timer time, time1

    void mvcGroupInit(Map args) {
        comprobarCodigo()
        precio()
        model.own = args.own
        def nombres = inventarioDBService.getNombreProductos()
        def articulos = inventarioDBService.getCodigoArticulos()
        edt {model.articulos.addAll(nombres); model.articulos.add('Ingresar nuevo...')}
    }

    def salir = {
        app.event("VentanaArticuloCerrada")
        destroyMVCGroup('newArticle')
    }

    def articulosCambio = { evt = null ->
        if (evt.getItem().equals("Ingresar nuevo...")) {
            def pantalla = view.nuevoNombreScreen
            pantalla.setLocationRelativeTo(view.newArticleScreen)
            pantalla.show()
            pantalla.getRootPane().setDefaultButton(view.aceptarFamiliaButton)
            view.familiaText.requestFocusInWindow()
        }
    }

    def foco = { evt = null ->
        if (evt.component.name.equals("aceptarBoton")) {
            view.newArticleScreen.getRootPane().setDefaultButton(view.aceptarBoton)
        } else if (evt.component.name.equals("salirBoton")) {
            view.newArticleScreen.getRootPane().setDefaultButton(view.salirBoton)
        } else if (evt.component.name.equals("aceptarFamiliaButton")) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.aceptarFamiliaButton)
        } else if (evt.component.name.equals("cancelarFamiliaButton")) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.cancelarFamiliaButton)
        } else if (evt.component.name.equals("familiaText")) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.aceptarFamiliaButton)
        } else if (evt.component.name.equals("codigoText")) {
            time.start()
        } else if (evt.component.name.equals("psText")) {
            time1.start()
        }
    }

    def focoLost = { evt = null ->
        if (evt.component.name.equals("codigoText")) {
            time.stop()
        } else if (evt.component.name.equals('psText')) {
            time1.stop()
        }
    }

    def cancel = { evt = null ->
        view.nombreCombo.setSelectedIndex(-1)
        view.familiaText.setText('')
        view.nuevoNombreScreen.setVisible(false)
    }

    def crearFamilia = { evt = null ->

        def lista = inventarioDBService.getNombreProductos()
        def respuesta = view.familiaText.getText()
        for (a in lista) {
            if (a.equalsIgnoreCase(respuesta)) {
                JOptionPane.showMessageDialog(view.nuevoNombreScreen, "Ya existe la familia", 'Error', JOptionPane.ERROR_MESSAGE)
                view.familiaText.setText('')
                view.familiaText.requestFocusInWindow()
                return
            }
        }
        inventarioDBService.setNombreProducto(respuesta.toUpperCase())
        def listaNueva = inventarioDBService.getNombreProductos()

        def art = model.articulos.clear()
        edt {
            model.articulos.addAll(listaNueva)
            model.articulos.add("Ingresar nuevo...")
        }
        view.nombreCombo.setSelectedItem(respuesta)

        view.nuevoNombreScreen.setVisible(false)
        view.familiaText.setText('')
    }

    def comprobarCodigo() {
        time = new Timer(1000, { ActionEvent e ->
            def codigos = inventarioDBService.getCodigoArticulos()
            def nuevoCodigo = view.codigoText.getText()
            codigos.each {
                if (it.equals(nuevoCodigo)) {
                    JOptionPane.showMessageDialog(view.newArticleScreen, "El codigo ya existe", "Error", JOptionPane.ERROR_MESSAGE)
                    view.codigoText.setText('')
                }
            }
        } as ActionListener)
    }

    def precio() {
        time1 = new Timer(1000, {ActionEvent ->
            def c = 0
            def entero = 0
            def fraccion = 0
            try {
                def a = 0

                a = Double.parseDouble(view.psText.getText())

                c = a + (a * 0.12)
                entero = (BigInteger) c
                fraccion = c - entero
            } catch (NumberFormatException e) {}
            if (fraccion > 0.40 && fraccion <= 0.49) {
                def f = 0.5;
                view.pvpText.setText(String.valueOf(entero + f))
            } else {
                view.pvpText.setText(String.valueOf(Math.round(c)))
            }
        } as ActionListener)
    }

    def crearArticulo = {evt = null ->

        if (view.codigoText.getText().equals('')) {
            view.ba1.setText('campo no puede estar vacio')
            view.ba1.setVisible(true)
            view.codigoText.setText('')
            view.codigoText.requestFocusInWindow()
            doOutside {Thread.sleep(3000)}
            view.ba1.setVisible(false)
        } else if (view.nombreCombo.getSelectedIndex() == -1) {
            view.ba2.setText('debe seleccionar una familia')
            view.ba2.setVisible(true)
            view.nombreCombo.requestFocusInWindow()
            doOutside { Thread.sleep(3000)}
            view.ba2.setVisible(false)
        } else if (view.stockCombo.getSelectedIndex() == -1) {
            view.ba7.setText('debe seleccionar una valor')
            view.ba7.setVisible(true)
            view.stockCombo.requestFocusInWindow()
            doOutside { Thread.sleep(3000)}
            view.ba7.setVisible(false)
        } else if (view.descripcionText.getText().equals('')) {
            view.ba3.setText('campo no puede estar vacio')
            view.ba3.setVisible(true)
            view.descripcionText.setText('')
            view.descripcionText.requestFocusInWindow()
            doOutside {Thread.sleep(3000)}
            view.ba3.setVisible(false)
        } else if (view.puText.getText().equals('')) {
            view.ba4.setText('campo no puede estar vacio')
            view.ba4.setVisible(true)
            view.puText.setText('')
            view.puText.requestFocusInWindow()
            doOutside {Thread.sleep(3000)}
            view.ba4.setVisible(false)
        } else if (view.psText.getText().equals('')) {
            view.ba5.setText('campo no puede estar vacio')
            view.ba5.setVisible(true)
            view.psText.setText('')
            view.psText.requestFocusInWindow()
            doOutside {Thread.sleep(3000)}
            view.ba5.setVisible(false)
        } else if (view.pvpText.getText().equals('')) {
            view.ba6.setText('campo no puede estar vacio')
            view.ba6.setVisible(true)
            view.pvpText.setText('')
            view.pvpText.requestFocusInWindow()
            doOutside {Thread.sleep(3000)}
            view.ba6.setVisible(false)
        } else {
            String nombre = (String) view.nombreCombo.getSelectedItem()
            String codigo = view.codigoText.getText()
            def pvpres
            try {
                pvpres = Double.parseDouble(view.pvpText.getText())
            } catch (NumberFormatException e) {
                view.ba6.setText('debe ingresar un numero valido')
                view.ba6.setVisible(true)
                view.pvpText.setText('')
                view.pvpText.requestFocusInWindow()
                doOutside {Thread.sleep(3000)}
                view.ba6.setVisible(false)
                return
            }
            def pUnitario
            try {
                pUnitario = Double.parseDouble(view.puText.getText())
            } catch (NumberFormatException e) {
                view.ba4.setText('debe ingresar un numero valido')
                view.ba4.setVisible(true)
                view.puText.setText('')
                view.puText.requestFocusInWindow()
                doOutside {Thread.sleep(3000)}
                view.ba4.setVisible(false)
                return
            }
            int stock = Integer.parseInt((String) view.stockCombo.getSelectedItem())
            def pSinIva
            try {
                pSinIva = Double.parseDouble(view.psText.getText())
            } catch (NumberFormatException e) {
                view.ba5.setText('debe ingresar un numero valido')
                view.ba5.setVisible(true)
                view.psText.setText('')
                view.psText.requestFocusInWindow()
                doOutside {Thread.sleep(3000)}
                view.ba5.setVisible(false)
                return
            }
            def pvp
            try {
                pvp = Double.parseDouble(view.pvpText.getText())
            } catch (NumberFormatException e) {
                view.ba6.setText('campo no puede estar vacio')
                view.ba6.setVisible(true)
                view.pvpText.setText('')
                view.pvpText.requestFocusInWindow()
                doOutside {Thread.sleep(3000)}
                view.ba6.setVisible(false)
                return
            }
            String descripcion = view.descripcionText.getText()
            def precioLotes = Math.round(pvp * stock)

            String nte = view.nteText.getText()
            String adicional = view.adicionalText.getText()
            String nPrecio = view.nPrecioText.getText()
            String factura = view.facturaText.getText()

            if (nte.isEmpty()) {
                nte = "N/A"
            } else if (adicional.isEmpty()) {
                adicional = "N/A"
            } else if (nPrecio.isEmpty()) {
                nte = "N/A"
            } else if (factura.isEmpty()) {
                factura = "N/A"
            }

            inventarioDBService.setNuevoarticulo(nombre, codigo, pUnitario, stock, pSinIva, pvp, descripcion, precioLotes, nte, adicional, factura, nPrecio)

            int result = JOptionPane.showConfirmDialog(view.newArticleScreen, "Ha creado un nuevo articulo\n desea crear otro?", "Aviso", JOptionPane.YES_NO_OPTION)
            if (result == JOptionPane.YES_OPTION) {
                view.nombreCombo.setSelectedIndex(-1)
                view.codigoText.setText('')
                view.puText.setText('')
                view.stockCombo.setSelectedIndex(-1)
                view.psText.setText('')
                view.pvpText.setText('')
                view.descripcionText.setText('')
            } else {
                app.event("VentanaArticuloCerrada")
                destroyMVCGroup('newArticle')
            }
        }
    }
}
