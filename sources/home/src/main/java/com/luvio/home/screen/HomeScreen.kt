package com.luvio.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luvio.ui_atoms.R
import com.luvio.ui_core.theme.AppTheme

@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = AppTheme.sizes.padding),
            text = stringResource(com.luvio.ui_core.R.string.app_name),
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Image(
            modifier = Modifier
                .padding(top = 96.dp)
                .size(256.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.ic_ready_for_travel),
            contentDescription = ""
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = AppTheme.sizes.padding),
            text = "Упс..\n Похоже, что приложение еще не готово\nПриходи чуть позже!",
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium
        )
    }
}