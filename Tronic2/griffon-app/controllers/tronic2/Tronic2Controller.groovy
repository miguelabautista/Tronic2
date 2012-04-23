package tronic2

import java.awt.Toolkit
import java.awt.event.KeyEvent
import javax.swing.JOptionPane

class Tronic2Controller {
    def model
    def view
    def usuarioDBService

    def onStartupEnd = {
        def tmpList = usuarioDBService.getUsuario()
        edt {model.usuario.addAll(tmpList)}
    }
    //TODO arreglar al final. determinar si destruir el group principal
    def entrar = { evt = null ->
        doOutside {
            String usuario = (String) view.passwordCombo.getSelectedItem()
            char[] respuesta = view.passwordText.getPassword()
            char[] clave = usuarioDBService.getClave(usuario).toCharArray()
            doLater {
                if (Arrays.equals(respuesta, clave)) {
                    def mvc = buildMVCGroup('principalScreen', [user: usuario])
                    def pantalla = mvc.view.firstScreen
                    pantalla.show()
                    // view.bienvenidoScreen.setVisible(false)
                    destroyMVCGroup('tronic2')
                } else {
                    JOptionPane.showMessageDialog(view.bienvenidoScreen, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE)
                    view.passwordText.setText('')
                    view.passwordText.requestFocusInWindow()
                }
                Arrays.fill(respuesta, '0' as char)
                Arrays.fill(clave, '0' as char)
            }
        }
    }

    def obtenerClave = { evt = null ->
        def mvc = buildMVCGroup([user: usuarioDBService.getUsuario()], 'obtainingPassword')
        def pantalla = mvc.view.obtainingScreen
        pantalla.show()
    }

    def salir = { evt = null ->
        app.shutdown()
    }

    def foco = { evt = null ->
        doLater {
            if (evt.getComponent().getName().equals('ingresarButton')) {
                view.bienvenidoScreen.rootPane.setDefaultButton(view.ingresarButton)
            } else if (evt.getComponent().getName().equals('salirButton')) {
                view.bienvenidoScreen.rootPane.setDefaultButton(view.salirButton)
            } else if (evt.getComponent().getName().equals('ingresarPasswordButton')) {
                view.bienvenidoScreen.rootPane.setDefaultButton(view.ingresarPasswordButton)
            } else if (evt.getComponent().getName().equals('passwordCombo') && view.ingresarButton.enabled != false) {
                view.bienvenidoScreen.rootPane.setDefaultButton(view.ingresarButton)
            } else if (evt.getComponent().getName().equals('passwordText')) {
                view.bienvenidoScreen.rootPane.setDefaultButton(view.ingresarButton)
            }
        }
    }

    def comboItem = { evt = null ->
        if (view.passwordCombo.getSelectedIndex() != -1) {
            view.passwordText.setEnabled(true)
        }
    }

    def capsLock = { evt = null ->
        boolean locking = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
        if (locking) {
            view.ba1.setVisible(true)
        } else {
            view.ba1.setVisible(false)
        }
    }


}
