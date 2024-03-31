package jva.cloud.userregistrations.presentation.viewmodel.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.userregistrations.domain.model.Event
import jva.cloud.userregistrations.domain.model.EventType
import jva.cloud.userregistrations.domain.model.User
import jva.cloud.userregistrations.domain.usecase.user.UserEventService
import jva.cloud.userregistrations.util.ConstantApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userEventService: UserEventService) :
    ViewModel() {

    private val _state = mutableStateOf(UserViewModelState())
    val state = _state

    private fun openDialog(): Unit {
        dialogTrueState()
    }

    fun onDialogConfirm(): Unit {
        dialogFalseState()
    }

    fun onDialogDismiss(): Unit {
        dialogFalseState()
    }

    fun updateParameterStatus(
        nameUser: String = ConstantApp.EMPTY,
        email: String = ConstantApp.EMPTY,
        password: String = ConstantApp.EMPTY,
        role: String = ConstantApp.EMPTY
    ): Unit {
        _state.value = _state.value.copy(
            nameUser = updateUserAttributeField(
                nameUser,
                _state.value.nameUser
            ),
            email = updateUserAttributeField(
                email,
                state.value.email
            ),
            password = updateUserAttributeField(
                password,
                state.value.password
            ),
            role = updateUserAttributeField(role, state.value.role)
        )
    }

    fun sendCreateUser(): Unit {
        viewModelScope.launch(context = Dispatchers.IO) {
            val user = User(
                name = state.value.nameUser,
                email = state.value.email,
                password = state.value.password,
                role = state.value.role
            )

            if (validateAfterSaveEmpty(user = user)) {
                _state.value = _state.value.copy(showDialogError = ConstantApp.TRUE)
                openDialog()
            } else {
                val userEvent: Event<Any> =
                    Event(
                        topicName = ConstantApp.TOPIC_USER,
                        type = EventType.CREATE,
                        messages = user
                    )

                userEventService.sendEvent(event = userEvent)
                openDialog()
                resetState()
            }
        }
    }

    private fun resetState(): Unit {
        _state.value = _state.value.copy(role = "", password = "", nameUser = "", email = "")
    }

    private fun dialogFalseState(): Unit {
        _state.value =
            _state.value.copy(showDialog = ConstantApp.FALSE, showDialogError = ConstantApp.FALSE)
    }

    private fun dialogTrueState(): Unit {
        _state.value = _state.value.copy(showDialog = true)
    }

    private fun updateUserAttributeField(valueNew: String, valueOld: String): String {
        val setValueNew: Boolean = valueNew.isNotEmpty() || valueOld.length <= ConstantApp.ONE
        return if (setValueNew) valueNew else valueOld
    }

    private fun validateAfterSaveEmpty(user: User): Boolean {
        return user.email.isEmpty() || user.name.isEmpty() || user.role.isEmpty() || user.password.isEmpty()
    }
}