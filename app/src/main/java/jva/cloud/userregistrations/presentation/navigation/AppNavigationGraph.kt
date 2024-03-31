package jva.cloud.userregistrations.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jva.cloud.userregistrations.presentation.view.user.CreateUserView
import jva.cloud.userregistrations.presentation.view.user.CreatedUsersView
import jva.cloud.userregistrations.presentation.view.user.UpdateUserView

@Composable
fun AppNavigationGraph(): Unit {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Router.REGISTER) {
        composable(route = Router.REGISTER) {
            CreateUserView(navigateToCreated = { navController.navigateToCreated() })
        }
        composable(route = Router.CREATED) {
            CreatedUsersView(
                navigateToUpdate = { id, name, email, role ->
                    navController.navigateToUpdate(
                        id, name, email, role
                    )
                },
                navigateToRegister = { navController.navigateToRegister() }
            )
        }
        composable(
            route = Router.UPDATE,
            arguments = listOf(
                navArgument(RouterOption.UpdateParam.ID) {
                    requireNotNull(RouterOption.UpdateParam.ID)
                    type = NavType.StringType
                },
                navArgument(RouterOption.UpdateParam.NAME) {
                    requireNotNull(RouterOption.UpdateParam.NAME)
                    type = NavType.StringType
                },
                navArgument(RouterOption.UpdateParam.EMAIL) {
                    requireNotNull(RouterOption.UpdateParam.EMAIL)
                    type = NavType.StringType
                },
                navArgument(RouterOption.UpdateParam.ROLE) {
                    requireNotNull(RouterOption.UpdateParam.ROLE)
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(RouterOption.UpdateParam.ID)
            val name = backStackEntry.arguments?.getString(RouterOption.UpdateParam.NAME)
            val email = backStackEntry.arguments?.getString(RouterOption.UpdateParam.EMAIL)
            val role = backStackEntry.arguments?.getString(RouterOption.UpdateParam.ROLE)
            UpdateUserView({ navController.navigateToCreated() }, id!!, name!!, email!!, role!!)
        }
    }
}