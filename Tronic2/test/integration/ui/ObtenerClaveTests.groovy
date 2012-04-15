package ui

import org.fest.swing.fixture.*
import griffon.fest.FestSwingTestCase

class ObtenerClaveTests extends FestSwingTestCase {
    /*
void testClave() {
window.with{
button('ingresarPasswordButton').click()
dialog().comboBox('usuarioCombo').selectItem('ADMINISTRADOR')
dialog().button('aceptarBoton').click()
dialog('obtainingScreen2').comboBox('preguntasCombo').selectItem(0)
dialog('obtainingScreen2').textBox('preguntasText').enterText("betty")
dialog('obtainingScreen2').button('aceptarBotonPregunta').click()
dialog('obtainingScreen2').optionPane().requireInformationMessage()//.requireMessage("su clave es: \\d+")
}
}                                                                        */

    void testEliminarPregunta() {
        window.with {
            menuItem('usuarioItem').click()
            dialog('usuariosScreen').label('preguntasLabel').click()
            dialog('questionsScreen').textBox('claveField').enterText('a')
            dialog('questionsScreen').button('aceptarButton').click()
            dialog('questionsScreen2').comboBox('preguntasCombo').selectItem(0)
            dialog('questionsScreen2').button('eliminarButton').click()
            dialog('questionsScreen2').optionPane().yesButton()
        }
    }

    protected void onSetUp() {
        // buildMVCGroup('principalScreen', [user:'ADMINISTRADOR'])
    }

    protected void onTearDown() {
    }

    protected FrameFixture initWindow() {
        app.buildMVCGroup('principalScreen', [user: 'ADMINISTRADOR'])
        return new FrameFixture(app.windowManager.windows[1])
    }
}
