package tronic2.inventario

import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.EventList

class NewArticleModel {
    EventList articulos = new BasicEventList<>()
    def items = 1..200
    def own

}