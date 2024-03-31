package jva.cloud.userregistrations.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import jva.cloud.userregistrations.ui.theme.Purple80

@Composable
fun MyOutlinedTextField(
    fieldName: String,
    text: String,
    imageVector: ImageVector,
    keyboardOptions: KeyboardOptions,
    onValueChange: (value: String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = ShapeDefaults.ExtraSmall),
        label = { Text(text = fieldName) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple80,
            focusedLabelColor = Purple80,
            cursorColor = Purple80,
        ),
        keyboardOptions = keyboardOptions,
        value = text,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = "")
        }
    )
    Spacer(modifier = Modifier.height(height = 20.dp))
}