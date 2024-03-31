package jva.cloud.userregistrations.presentation.viewmodel.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jva.cloud.userregistrations.data.dto.UserDto
import jva.cloud.userregistrations.domain.model.Event
import jva.cloud.userregistrations.domain.model.EventType
import jva.cloud.userregistrations.domain.usecase.user.GetUsersService
import jva.cloud.userregistrations.domain.usecase.user.UserEventService
import jva.cloud.userregistrations.util.ConstantApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatedUsersViewModel @Inject constructor(
    private val getUsersService: GetUsersService,
    private val userEventService: UserEventService
) :
    ViewModel() {
    private val _state = mutableStateOf(CreatedUsersViewModelState())
    val state = _state

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            getAllUser()
        }
    }


    fun deleteUser(user: UserDto): Unit {
        viewModelScope.launch(context = Dispatchers.IO) {
            val userEvent: Event<Any> =
                Event(topicName = ConstantApp.TOPIC_USER, type = EventType.DELETE, messages = user)
            userEventService.sendEvent(event = userEvent)
            getTheUsersAfterDeleting()
        }
    }

    private fun showProgressIndicator(show: Boolean): Unit {
        _state.value = _state.value.copy(progressIndicator = show)
    }

    private suspend fun getTheUsersAfterDeleting(): Unit {
        showProgressIndicator(ConstantApp.TRUE)
        getAllUser()
        showProgressIndicator(ConstantApp.FALSE)
    }

    private suspend fun getAllUser(): Unit {
        val users = getUsersService.getAllUser()
        _state.value = _state.value.copy(users = users)
    }
}