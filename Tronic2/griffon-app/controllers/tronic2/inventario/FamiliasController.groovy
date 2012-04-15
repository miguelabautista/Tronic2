package tronic2.inventario

import javax.swing.JOptionPane

class FamiliasController {
    // these will be injected by Griffon
    def model
    def view
    def inventarioDBService

    def salir = {evt = null ->
        destroyMVCGroup('familias')
    }

    def crearFamilia = { evt = null ->
        if (view.familiaText.text.size() == 0) {
            view.ba1.setVisible(true)
            doOutside {Thread.sleep(2000)}
            view.ba1.setVisible(false)
        } else {
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
            app.event('Familia')
            destroyMVCGroup('familias')
        }
    }

    def foco = { evt = null ->
        if (evt.component.name.equals('familiaText')) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.aceptarFamiliaButton)
        } else if (evt.component.name.equals('aceptarFamiliaButton')) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.aceptarFamiliaButton)
        } else if (evt.component.name.equals('cancelarFamiliaButton')) {
            view.nuevoNombreScreen.getRootPane().setDefaultButton(view.cancelarFamiliaButton)
        }
    }
}
