package tronic2.inventario

import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.SortedList
import ca.odell.glazedlists.TextFilterator
import com.tronic.Inventario
import net.sourceforge.gvalidation.annotation.Validatable

@Validatable(realTime = true)
class AdministracionModel {
    @Bindable boolean selection
    @Bindable boolean textFieldsEnable = false
    @Bindable boolean cancelar = false
    @Bindable boolean guardar = false
    @Bindable boolean eliminar = false
    @Bindable boolean modificar = false
    @Bindable boolean nuevo = true

    EventList articulos = new SortedList(new BasicEventList<>(), {a, b -> a.codigo <=> b.codigo} as Comparator)
    EventList nombres = new BasicEventList()
    List busquedaArticulos = new ArrayList()
    List busquedaFamilias = new ArrayList()

    def tipoBusqueda = ['Codigo', 'Familia']

    TextFilterator filterator = { List<String> list, def e ->
        Inventario inv = (Inventario) e
        list.add(inv.nombre)
        list.add(inv.codigo)
    } as TextFilterator

    @Bindable def codigo
    @Bindable def nombre
    @Bindable def descripcion
    @Bindable def stock
    @Bindable def precioUnitario
    @Bindable def precioSinIva
    @Bindable def pvp
    @Bindable def precioLotes
    @Bindable def nte
    @Bindable def factura
    @Bindable def adicional
    @Bindable def n_precio

    @Bindable def nombreNuevo
    @Bindable def descripcionNuevo
    @Bindable def stockNuevo
    @Bindable def precioUnitarioNuevo
    @Bindable def precioSinIvaNuevo
    @Bindable def pvpNuevo
    @Bindable def precioLotesNuevo
    @Bindable def nteNuevo
    @Bindable def facturaNuevo
    @Bindable def adicionalNuevo
    @Bindable def n_precioNuevo

    static constraints = {
        nombreNuevo(blank: false, nullable: false)
        descripcionNuevo(blank: false)
        stockNuevo(matches: /(\d)+/, blank: false, nullable: false)
        precioUnitarioNuevo(matches: /(\d)+(\.(\d)+)?/, blank: false, nullable: false)
        precioSinIvaNuevo(matches: /(\d)+(\.(\d)+)?/, blank: false, nullable: false)
        pvpNuevo(matches: /(\d)+(\.(\d)+)?/, blank: false, nullable: false)
        precioLotesNuevo(blank: false, nullable: false,)
    }

    def isNumber = { val ->
        if (!val) return true
        try {
            Double.parseDouble(val)
        } catch (NumberFormatException e) {
            false
        }
    }

}