package jva.cloud.userregistrations.presentation.view.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.userregistrations.R
import jva.cloud.userregistrations.presentation.common.MyAlertDialog
import jva.cloud.userregistrations.presentation.common.MyOutlinedTextField
import jva.cloud.userregistrations.presentation.viewmodel.user.UpdateUserViewModel

@Composable
fun UpdateUserView(
    navigateToCreated: () -> Unit,
    id: String,
    name: String,
    email: String,
    role: String,
    updateUserViewModel: UpdateUserViewModel = hiltViewModel(),
): Unit {
    updateUserViewModel.initialState(
        id = id.toLong(),
        nameUser = name,
        email = email,
        role = role,
    )
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        MyBody(navigateToCreated = navigateToCreated, updateUserViewModel = updateUserViewModel)
        MyAlertDialog(
            showDialog = updateUserViewModel.state.value.showDialog,
            title = if (updateUserViewModel.state.value.showDialogError) stringResource(id = R.string.error) else stringResource(
                id = R.string.update_user
            ),
            text = if (updateUserViewModel.state.value.showDialogError) stringResource(id = R.string.error_data_not_password) else stringResource(
                id = R.string.update_user_registered
            ),
            onDismiss = updateUserViewModel::onDialogDismiss,
            onConfirm = updateUserViewModel::onDialogConfirm
        )
    }
}

@Composable
private fun MyBody(navigateToCreated: () -> Unit, updateUserViewModel: UpdateUserViewModel): Unit {
    Column(modifier = Modifier.fillMaxSize()) {
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_name),
            text = updateUserViewModel.state.value.nameUser,
            imageVector = Icons.Default.Person,
            keyboardOptions = KeyboardOptions.Default,
            onValueChange = { updateUserViewModel.updateParameterStatus(nameUser = it) })
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_email),
            text = updateUserViewModel.state.value.email,
            imageVector = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { updateUserViewModel.updateParameterStatus(email = it) })
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_password),
            text = updateUserViewModel.state.value.password,
            imageVector = Icons.Default.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { updateUserViewModel.updateParameterStatus(password = it) })
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_role),
            text = updateUserViewModel.state.value.role,
            imageVector = Icons.Default.AccountBox,
            keyboardOptions = KeyboardOptions.Default,
            onValueChange = { updateUserViewModel.updateParameterStatus(role = it) })
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = { navigateToCreated() }) {
            Text(text = stringResource(id = R.string.return_user_history))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                updateUserViewModel.updateUser()
            }
        ) {
            Text(text = stringResource(id = R.string.update_user))
        }
    }
}
