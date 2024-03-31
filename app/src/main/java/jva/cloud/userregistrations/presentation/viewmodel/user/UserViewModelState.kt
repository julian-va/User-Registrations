package jva.cloud.userregistrations.presentation.viewmodel.user

import jva.cloud.userregistrations.util.ConstantApp

data class UserViewModelState(
    val nameUser: String = ConstantApp.EMPTY,
    val email: String = ConstantApp.EMPTY,
    val password: String = ConstantApp.EMPTY,
    val role: String = ConstantApp.EMPTY,
    val showDialog: Boolean = ConstantApp.FALSE,
    val showDialogError: Boolean = ConstantApp.FALSE,
)
