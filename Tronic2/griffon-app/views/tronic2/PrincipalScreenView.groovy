package tronic2

import static javax.swing.KeyStroke.getKeyStroke

application(name: 'firstScreen', id: 'firstScreen', title: 'TRONIC',
        preferredSize: [800, 600],
        pack: true,
        // extendedState: Frame.MAXIMIZED_BOTH,
        locationByPlatform: true,
        iconImage: imageIcon('/LOGO.png').image) {
    menuBar {
        menu(text: 'Opciones', mnemonic: 'O') {
            menuItem(name: 'usuarioItem', text: 'usuarios', accelerator: getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), actionPerformed: controller.usuarios)
            menuItem(name: 'exit', text: 'Salir', accelerator: getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionPerformed: controller.salir)
        }
        menu(text: 'Inventario', mnemonic: 'I') {
            menuItem(name: 'inventarioItem', text: 'Ingresar', accelerator: getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK), actionPerformed: controller.ingresarArticulo)
            menuItem(name: 'inventarioItem2', text: 'Administracion', accelerator: getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK), actionPerformed: controller.administracionInventario)
        }

    }
    panel() {
        migLayout()
        jxtaskPaneContainer(constraints: "dock west") {
            jxtaskPane(title: "Task group 1") {
                jxlabel("Action 1")
            }
        }


    }
}
firstScreen.setLocationRelativeTo(null)
