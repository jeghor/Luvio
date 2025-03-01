package com.luvio.ui_core.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.custom_views.LuvioButton
import com.luvio.ui_core.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuvioBottomDialog(
    coroutineScope: CoroutineScope,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    message: String = stringResource(com.luvio.ui_core.R.string.ok),
    onStateUpdate: (Boolean) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            coroutineScope.launch {
                onStateUpdate(false)
            }
        },
        dragHandle = dragHandle,
        containerColor = AppTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = AppTheme.sizes.padding),
                text = message,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textPrimary,
                style = AppTheme.typography.titleSmall
            )

            LuvioButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.sizes.buttonHeight)
                    .width(AppTheme.sizes.smallButtonWidth)
                    .padding(horizontal = 64.dp),
                text = stringResource(com.luvio.ui_core.R.string.ok)
            ) {
                coroutineScope.launch {
                    onStateUpdate(false)
                }
            }
        }

    }
}