package com.tronic

import ca.odell.glazedlists.matchers.AbstractMatcherEditor
import ca.odell.glazedlists.matchers.Matcher

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComboBox

/**
 * Created with IntelliJ IDEA.
 * User: MIGUEL
 * Date: 09/04/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
class InventarioMatcher extends AbstractMatcherEditor implements ActionListener {

    JComboBox inventarioChooser

    InventarioMatcher(JComboBox combo) {
        this.inventarioChooser = combo
        this.inventarioChooser.addActionListener(this)
    }


    @Override
    void actionPerformed(ActionEvent e) {
        final String articulo = (String) this.inventarioChooser.getSelectedItem()
        if (articulo == null) {
            this.fireMatchAll()
        } else {
            this.fireChanged(new InvenMatcher(arti))
        }
    }

    private static class InvenMatcher implements Matcher {

        final String arti

        InvenMatcher(String arti) {
            this.arti = arti
        }

        @Override
        boolean matches(Object e) {
            final Inventario inv = (Inventario) e
            return this.arti.equals(inv.codigo)
        }
    }
}
