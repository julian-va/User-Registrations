package jva.cloud.userregistrations.presentation.view.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.userregistrations.R
import jva.cloud.userregistrations.presentation.common.MyAlertDialog
import jva.cloud.userregistrations.presentation.common.MyOutlinedTextField
import jva.cloud.userregistrations.presentation.viewmodel.user.UserViewModel

@Composable
fun CreateUserView(
    navigateToCreated: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
): Unit {
    MyBody(navigateToCreated = { navigateToCreated() }, userViewModel)
    ShowMyAlertDialog(
        showDialog = userViewModel.state.value.showDialog,
        showDialogError = userViewModel.state.value.showDialogError,
        onDismiss = { userViewModel.onDialogDismiss() },
        onConfirm = { userViewModel.onDialogConfirm() })
}


@Composable
fun MyBody(navigateToCreated: () -> Unit, userViewModel: UserViewModel): Unit {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(30.dp),
            text = stringResource(R.string.user_registration),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(value = 40F, type = TextUnitType.Sp)
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyOutlinedTextField(
                fieldName = stringResource(R.string.user_name),
                text = userViewModel.state.value.nameUser,
                imageVector = Icons.Default.Person,
                keyboardOptions = KeyboardOptions.Default,
                onValueChange = { userViewModel.updateParameterStatus(nameUser = it) })
            MyOutlinedTextField(
                fieldName = stringResource(R.string.user_email),
                text = userViewModel.state.value.email,
                imageVector = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { userViewModel.updateParameterStatus(email = it) })
            MyOutlinedTextField(
                fieldName = stringResource(R.string.user_password),
                text = userViewModel.state.value.password,
                imageVector = Icons.Default.Lock,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { userViewModel.updateParameterStatus(password = it) })
            MyOutlinedTextField(
                fieldName = stringResource(R.string.user_role),
                text = userViewModel.state.value.role,
                imageVector = Icons.Default.AccountBox,
                keyboardOptions = KeyboardOptions.Default,
                onValueChange = { userViewModel.updateParameterStatus(role = it) })

            Spacer(modifier = Modifier.size(size = 10.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                userViewModel.sendCreateUser()
            }) {
                Text(
                    text = stringResource(id = R.string.create_user),
                )
            }
            Spacer(modifier = Modifier.size(size = 10.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { navigateToCreated() }) {
                Text(
                    text = stringResource(id = R.string.user_history),
                )
            }

        }
    }
}

@Composable
private fun ShowMyAlertDialog(
    showDialog: Boolean,
    showDialogError: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
): Unit {
    val title =
        if (showDialogError) stringResource(id = R.string.error) else stringResource(id = R.string.created_user)
    val text =
        if (showDialogError) stringResource(id = R.string.error_data) else stringResource(id = R.string.created_user_registered)
    MyAlertDialog(
        showDialog = showDialog,
        title = title,
        text = text,
        onDismiss = onDismiss,
        onConfirm = onConfirm
    )
}
