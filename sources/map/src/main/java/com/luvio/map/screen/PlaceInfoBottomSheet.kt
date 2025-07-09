package com.luvio.map.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.theme.AppTheme
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceInfoBottomSheet(
    geoJsonInfo: String?,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (geoJsonInfo != null) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                //TODO: Stub just for showing data
                val jsonObject = JSONObject(geoJsonInfo)
                val name = jsonObject.optString("name")
                val street = jsonObject.optString("addr:street")

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Кафе $name",
                    style = AppTheme.typography.titleLarge,
                    color = AppTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Информация о точке:\n${
                        street.takeIf { it.isNotEmpty() }?.let { "Улица: $it" } ?: ""
                    }",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}