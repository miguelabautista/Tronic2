package tronic2.useroperations

import java.awt.Color
import java.awt.Toolkit
import java.awt.event.KeyEvent
import javax.swing.JOptionPane

class ChangePasswordController {
    // these will be injected by Griffon
    def model
    def view
    def usuarioDBService

    void mvcGroupInit(Map args) {
        model.usuario = args.user
    }

    def salir = {
        app.views.usuarios.passwordLabel.setForeground(Color.black)
        destroyMVCGroup('changePassword')
    }
    //TODO corregir error en validationes
    def cambiarClave = { evt = null ->
        if (model.originalPassword.equals('')) {
            view.ba1.setText('campo no puede estar vacio')
            view.ba1.setVisible(true)
            view.passwordText.setText('')
            doOutside {Thread.sleep(3000)}
            view.ba1.setVisible(false)
        } else if (model.newPassword.equals(model.originalPassword)) {
            view.ba2.setText('nueva clave no puede ser igual a la antigua clave')
            view.ba2.setVisible(true)
            view.passwordText2.setText('')
            doOutside { Thread.sleep(3000)}
            view.ba2.setVisible(false)
        } else if (model.newPassword.equals('')) {
            view.ba2.setText('campo no puede estar vacio')
            view.ba2.setVisible(true)
            view.passwordText2.setText('')
            doOutside {Thread.sleep(3000)}
            view.ba2.setVisible(false)
        } else if (!model.repeatPassword.equals(model.newPassword)) {
            view.ba3.setText('debe repetir correctamente la nueva clave')
            view.ba3.setVisible(true)
            view.passwordText3.setText('')
            doOutside {Thread.sleep(3000)}
            view.ba3.setVisible(false)
        } else if (model.repeatPassword.equals('')) {
            view.ba3.setText('campo no puede estar vacio')
            view.ba3.setVisible(true)
            view.passwordText3.setText('')
            doOutside {Thread.sleep(3000)}
            view.ba3.setVisible(false)
        } else {
            doLater {
                def res = usuarioDBService.getClave(model.usuario)
                def respuestaIngresada = view.passwordText.getText()
                if (res != respuestaIngresada) {
                    JOptionPane.showMessageDialog(view.passwordScreen, 'clave original incorrecta', 'Error', JOptionPane.ERROR_MESSAGE)
                    view.passwordText.setText('')
                    view.passwordText.requestFocusInWindow()
                    return
                }
                int result = JOptionPane.showConfirmDialog(view.passwordScreen, "Esta seguro que desea cambiar la clave?", "aviso", JOptionPane.INFORMATION_MESSAGE)
                if (result == JOptionPane.YES_OPTION) {
                    usuarioDBService.cambiarClave(model.usuario, model.repeatPassword)
                    JOptionPane.showMessageDialog(view.passwordScreen, "clave cambiada con exito", 'Exito', JOptionPane.INFORMATION_MESSAGE)
                    app.views.usuarios.passwordLabel.setForeground(Color.black)
                    destroyMVCGroup('changePassword')
                }
            }
        }

    }

    def capsLock(KeyEvent evt = null) {
        if (evt.component.name.equals('passwordText')) {
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba1.setText('Matuscula')
                view.ba1.setVisible(true)
            } else {
                view.ba1.setVisible(false)
            }
        } else if (evt.component.name.equals('passwordText2')) {
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba2.setText('Matuscula')
                view.ba2.setVisible(true)
            } else {
                view.ba2.setVisible(false)
            }
        } else if (evt.component.name.equals('passwordText3')) {
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba3.setText('Matuscula')
                view.ba3.setVisible(true)
            } else {
                view.ba3.setVisible(false)
            }
        }
    }

    def foc = { evt = null ->
        if (evt.component.name.equals('passwordText')) {
            view.passwordScreen.getRootPane().setDefaultButton(view.aceptarButton)
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba1.setText('Matuscula')
                view.ba1.setVisible(true)
            } else {
                view.ba1.setVisible(false)
            }
        } else if (evt.component.name.equals('passwordText2')) {
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba2.setText('Matuscula')
                view.ba2.setVisible(true)
            } else {
                view.ba2.setVisible(false)
            }
        } else if (evt.component.name.equals('passwordText3')) {
            def res = Toolkit.defaultToolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
            if (res) {
                view.ba3.setText('Matuscula')
                view.ba3.setVisible(true)
            } else {
                view.ba3.setVisible(false)
            }
        } else if (evt.component.name.equals('aceptarButton')) {
            view.passwordScreen.getRootPane().setDefaultButton(view.aceptarButton)
        } else if (evt.component.name.equals('salirButton')) {
            view.passwordScreen.getRootPane().setDefaultButton(view.salirButton)
        }
    }

    def focoPerdido = { evt = null ->
        if (evt.component.name.equals('passwordText')) {
            if (view.ba1.isVisible())
                view.ba1.setVisible(false)
        } else if (evt.component.name.equals('passwordText2')) {
            if (view.ba2.isVisible())
                view.ba2.setVisible(false)
        } else if (evt.component.name.equals('passwordText3')) {
            if (view.ba3.isVisible())
                view.ba3.setVisible(false)
        }
    }
}
