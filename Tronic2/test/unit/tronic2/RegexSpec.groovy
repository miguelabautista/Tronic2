package tronic2

import spock.lang.*

class RegexSpec extends Specification {

    @Unroll('listado')
    def 'comprobar si es numerico'() {
        expect:
        valor ==~ /(\d)+(\.(\d)+)?/

        where:

        valor << ["23.43", "23.23we", "43.aa", "32.as234"]
    }
}
