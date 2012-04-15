root {
    'groovy.swing.SwingBuilder' {
        controller = ['Threading']
        view = '*'
    }
}

root.'GlazedlistsGriffonAddon'.addon = true

root.'griffon.builder.swingxtras.SwingxtrasBuilder'.view = '*'

jx {
    'groovy.swing.SwingXBuilder' {
        view = '*'
    }
}

root.'griffon.builder.macwidgets.MacWidgetsBuilder'.view = '*'

root.'I18nGriffonAddon'.addon = true

