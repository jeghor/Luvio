package com.luvio.map.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.luvio.ui_atoms.R
import com.luvio.ui_core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    Box(
        Modifier.fillMaxSize()
    ) {
        CustomMapView()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.MediumAppBarCollapsedHeight)
                .background(
                    AppTheme.colors.background,
                    RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(horizontal = AppTheme.sizes.padding)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(
                        AppTheme.colors.background,
                        RoundedCornerShape(24.dp)
                    )
                    .border(1.dp, AppTheme.colors.primary, RoundedCornerShape(24.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp),
                    colorFilter = ColorFilter.tint(AppTheme.colors.primary),
                    painter = painterResource(R.drawable.ic_route),
                    contentDescription = "route"
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(com.luvio.ui_core.R.string.my_routes),
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textPrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = "filter"
            )
        }
    }
}

