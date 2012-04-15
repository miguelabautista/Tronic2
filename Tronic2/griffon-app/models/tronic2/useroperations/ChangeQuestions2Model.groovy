package tronic2.useroperations

import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.EventList

class ChangeQuestions2Model {
    EventList preguntas = new BasicEventList()
    def respuestaOriginal = ""
    @Bindable boolean habilitar
    @Bindable boolean textoCambia
}