package com.luvio.ui_core.custom_views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.theme.AppTheme

@Composable
fun TextButton(
    modifier: Modifier,
    @DrawableRes startIcon: Int,
    text: String
) {
    Row(
        modifier = modifier
            .height(AppTheme.sizes.defaultTextButtonHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(startIcon),
            modifier = Modifier.padding(end = 12.dp),
            contentDescription = "Start icon",
        )
        Text(
            modifier = Modifier.weight(1F),
            text = text,
            style = AppTheme.typography.bodySmall
        )
        Icon(
            painterResource(com.luvio.ui_atoms.R.drawable.ic_arrow),
            modifier = Modifier
                .padding(start = 12.dp),
            contentDescription = "End icon"
        )
    }
}

@Preview
@Composable
fun TextButtonPreview() {
    Surface {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            startIcon = com.luvio.ui_atoms.R.drawable.ic_camera,
            text = "Memoriesqwdqwdqdwdqdwdq"
        )
    }
}