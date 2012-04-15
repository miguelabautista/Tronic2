package ui

import org.fest.swing.fixture.*
import griffon.fest.FestSwingTestCase

class InventarioTests extends FestSwingTestCase {
    // instance variables:
    // app    - current application
    // window - value returned from initWindow()
    //          defaults to app.windowManager.windows[0]
    /*
    void testCrearNuevaFamilia() {
        window.with{
            menuItem('inventarioItem').click()
            dialog('newArticleScreen').comboBox('nombreCombo').selectItem("Ingresar nuevo...")
            dialog('nuevoNombreScreen').textBox('familiaText').enterText('maricon')
            dialog('nuevoNombreScreen').button('aceptarFamiliaButton').click()
            dialog('nuevoNombreScreen').optionPane().requireInformationMessage()
        }
    }*/

    void testAdministracion() {
        window.with {
            menuItem('inventarioItem2').click()
            dialog('administracionScreen').button('newButton').click()
            dialog('newArticleScreen').comboBox('nombreCombo').selectedItem("TECLADO")
            dialog('newArticleScreen').textBox('codigoText').enterText('00006')
            dialog('newArticleScreen').textBox('descripcionText').enterText('hola')
            dialog('newArticleScreen').textBox('puText').enterText('23.3')
            dialog('newArticleScreen').textBox('psText').enterText('00006')
            dialog('newArticleScreen').button('aceptarBoton').click()

            def cantidad = app.views.articulosTable.getRowCount()

            assertEquals 5, cantidad
        }
    }

    // protected void onSetUp() {
    // }

    // protected void onTearDown() {
    // }

    protected FrameFixture initWindow() {
        app.buildMVCGroup('principalScreen')
        return new FrameFixture(app.windowManager.windows[1])
    }
}
