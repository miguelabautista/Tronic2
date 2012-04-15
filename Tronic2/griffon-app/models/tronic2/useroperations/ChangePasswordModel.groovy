package tronic2.useroperations

import net.sourceforge.gvalidation.annotation.Validatable

@Validatable(realTime = true)
class ChangePasswordModel {
    def usuario
    @Bindable String originalPassword = ''
    @Bindable String newPassword = ''
    @Bindable String repeatPassword = ''

    static constraints = {
        originalPassword(blank: false, nullable: false)
        newPassword(blank: false, nullable: false, validator: {val, obj ->
            obj.properties['originalPassword'] != val
        })
        repeatPassword(blank: false, nullable: false, validator: {val, obj ->
            obj.properties['newPassword'] == val
        })
    }

}