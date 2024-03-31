package jva.cloud.userregistrations.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateToRegister(): Unit {
    this.navigate(Router.REGISTER)
}

fun NavController.navigateToCreated(): Unit {
    this.navigate(Router.CREATED)
}

fun NavController.navigateToUpdate(id: String, name: String, email: String, role: String): Unit {
    this.navigate("${RouterOption.UpdateParam.BASE_ROUTE}/$id/$name/$email/$role")
}