application {
    title = 'Tronic2'
    startupGroups = ['tronic2']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "familias"
    'familias' {
        model = 'tronic2.inventario.FamiliasModel'
        view = 'tronic2.inventario.FamiliasView'
        controller = 'tronic2.inventario.FamiliasController'
    }

    // MVC Group for "administracion"
    'administracion' {
        model = 'tronic2.inventario.AdministracionModel'
        view = 'tronic2.inventario.AdministracionView'
        controller = 'tronic2.inventario.AdministracionController'
    }

    // MVC Group for "usuarios"
    'usuarios' {
        model = 'tronic2.useroperations.UsuariosModel'
        view = 'tronic2.useroperations.UsuariosView'
        controller = 'tronic2.useroperations.UsuariosController'
    }

    // MVC Group for "obtainingPassword"
    'obtainingPassword' {
        model = 'tronic2.useroperations.ObtainingPasswordModel'
        view = 'tronic2.useroperations.ObtainingPasswordView'
        controller = 'tronic2.useroperations.ObtainingPasswordController'
    }

    // MVC Group for "obtainingPassword2"
    'obtainingPassword2' {
        model = 'tronic2.useroperations.ObtainingPassword2Model'
        view = 'tronic2.useroperations.ObtainingPassword2View'
        controller = 'tronic2.useroperations.ObtainingPassword2Controller'
    }

    // MVC Group for "changeQuestions"
    'changeQuestions' {
        model = 'tronic2.useroperations.ChangeQuestionsModel'
        view = 'tronic2.useroperations.ChangeQuestionsView'
        controller = 'tronic2.useroperations.ChangeQuestionsController'
    }

    // MVC Group for "changeQuestions3"
    'changeQuestions3' {
        model = 'tronic2.useroperations.ChangeQuestions3Model'
        view = 'tronic2.useroperations.ChangeQuestions3View'
        controller = 'tronic2.useroperations.ChangeQuestions3Controller'
    }

    // MVC Group for "changeQuestions2"
    'changeQuestions2' {
        model = 'tronic2.useroperations.ChangeQuestions2Model'
        view = 'tronic2.useroperations.ChangeQuestions2View'
        controller = 'tronic2.useroperations.ChangeQuestions2Controller'
    }

    // MVC Group for "changePassword"
    'changePassword' {
        model = 'tronic2.useroperations.ChangePasswordModel'
        view = 'tronic2.useroperations.ChangePasswordView'
        controller = 'tronic2.useroperations.ChangePasswordController'
    }

    // MVC Group for "newArticle"
    'newArticle' {
        model = 'tronic2.inventario.NewArticleModel'
        view = 'tronic2.inventario.NewArticleView'
        controller = 'tronic2.inventario.NewArticleController'
    }

    // MVC Group for "principalScreen"
    'principalScreen' {
        model = 'tronic2.PrincipalScreenModel'
        view = 'tronic2.PrincipalScreenView'
        controller = 'tronic2.PrincipalScreenController'
    }

    // MVC Group for "tronic2"
    'tronic2' {
        model = 'tronic2.Tronic2Model'
        view = 'tronic2.Tronic2View'
        controller = 'tronic2.Tronic2Controller'
    }

}
