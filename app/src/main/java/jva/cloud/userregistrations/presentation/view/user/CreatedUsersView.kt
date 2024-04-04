package jva.cloud.userregistrations.presentation.view.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.userregistrations.R
import jva.cloud.userregistrations.data.dto.UserDto
import jva.cloud.userregistrations.presentation.viewmodel.user.CreatedUsersViewModel

@Composable
fun CreatedUsersView(
    createdUsersViewModel: CreatedUsersViewModel = hiltViewModel(),
    navigateToUpdate: (String, String, String, String) -> Unit,
    navigateToRegister: () -> Unit
): Unit {
    val userList = createdUsersViewModel.state.value.users

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (userList.isNotEmpty()) {
            LazyColumn {
                items(userList, key = { user -> user.id }) { user ->
                    CardUserCreated(
                        userDto = user,
                        delete = { createdUsersViewModel.deleteUser(it) },
                        update = {
                            navigateToUpdate(it.id.toString(), it.name, it.email, it.role)
                        })
                }
                item {
                    NavigateToRegisterButton(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        navigateToRegister = { navigateToRegister() })
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        } else {
            UserEmpty { navigateToRegister() }
        }

        if (createdUsersViewModel.state.value.progressIndicator) {

            CircularProgressIndicator(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .align(Alignment.Center)
            )

        }
    }
}

@Composable
fun CardUserCreated(
    userDto: UserDto,
    delete: (user: UserDto) -> Unit,
    update: (user: UserDto) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextValueCard(textHeader = stringResource(R.string.user_name), text = userDto.name)
            TextValueCard(textHeader = stringResource(R.string.user_email), text = userDto.email)
            TextValueCard(textHeader = stringResource(R.string.user_role), text = userDto.role)
            TextValueCard(
                textHeader = stringResource(R.string.user_date_created),
                text = userDto.creationDate.toString()
            )
            TextValueCard(
                textHeader = stringResource(R.string.user_date_update),
                text = userDto.updateDate.toString()
            )
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                IconButton(onClick = { delete(userDto) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete user")
                }
                IconButton(onClick = { update(userDto) }) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "update user")
                }
            }

        }
    }
}

@Composable
fun TextValueCard(
    textHeader: String, text: String
) {
    Row {
        Text(
            text = "$textHeader : ",
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(2.dp),
            softWrap = true,
            maxLines = 1
        )
    }
    Spacer(modifier = Modifier.size(size = 2.dp))
}

@Composable
fun UserEmpty(navigateToRegister: () -> Unit): Unit {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(

            text = stringResource(R.string.user_history_empty),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(value = 40F, type = TextUnitType.Sp),
                textAlign = TextAlign.Center
            )
        )
        Icon(
            modifier = Modifier
                .size(50.dp),
            imageVector = Icons.Default.Person,
            contentDescription = stringResource(R.string.user_history_empty)
        )
        NavigateToRegisterButton(
            modifier = Modifier.padding(10.dp),
            navigateToRegister = { navigateToRegister() })
    }
}

@Composable
private fun NavigateToRegisterButton(modifier: Modifier, navigateToRegister: () -> Unit): Unit {
    Button(modifier = modifier,
        onClick = { navigateToRegister() }) {
        Text(text = "return to user creation")
    }
}