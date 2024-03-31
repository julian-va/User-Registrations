package jva.cloud.userregistrations.presentation.viewmodel.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.userregistrations.data.dto.UserUpdateDto
import jva.cloud.userregistrations.domain.model.Event
import jva.cloud.userregistrations.domain.model.EventType
import jva.cloud.userregistrations.domain.usecase.user.UserEventService
import jva.cloud.userregistrations.util.ConstantApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateUserViewModel @Inject constructor(private val userEventService: UserEventService) :
    ViewModel() {

    private val _state = mutableStateOf(UpdateUserViewModelState())
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

    fun initialState(
        id: Long,
        nameUser: String,
        email: String,
        password: String = ConstantApp.EMPTY,
        role: String
    ): Unit {
        if (state.value.ifInitial) {
            _state.value = _state.value.copy(
                id = id,
                nameUser = nameUser,
                email = email,
                password = password,
                role = role,
                ifInitial = false
            )
        }
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

    fun updateUser(): Unit {
        viewModelScope.launch(context = Dispatchers.IO) {
            val user = UserUpdateDto(
                id = state.value.id,
                name = state.value.nameUser,
                email = state.value.email,
                password = state.value.password,
                role = state.value.role,
            )
            if (validateAfterSaveEmpty(user = user)) {
                _state.value = _state.value.copy(showDialogError = ConstantApp.TRUE)
                openDialog()
            } else {
                val userEvent: Event<Any> =
                    Event(
                        topicName = ConstantApp.TOPIC_USER,
                        type = EventType.UPDATE,
                        messages = user
                    )
                userEventService.sendEvent(event = userEvent)
                openDialog()
            }
        }
    }

    private fun updateUserAttributeField(valueNew: String, valueOld: String): String {
        val setValueNew: Boolean = valueNew.isNotEmpty() || valueOld.length <= ConstantApp.ONE
        return if (setValueNew) valueNew else valueOld
    }

    private fun dialogFalseState(): Unit {
        _state.value =
            _state.value.copy(showDialog = ConstantApp.FALSE, showDialogError = ConstantApp.FALSE)
    }

    private fun dialogTrueState(): Unit {
        _state.value = _state.value.copy(showDialog = ConstantApp.TRUE)
    }

    private fun validateAfterSaveEmpty(user: UserUpdateDto): Boolean {
        return user.email.isEmpty() || user.name.isEmpty() || user.role.isEmpty()
    }
}