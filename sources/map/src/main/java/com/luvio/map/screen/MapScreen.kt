package com.luvio.map.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

        // Appbar with buttons "My routes" and "Filter"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.MediumAppBarCollapsedHeight)
                .background(
                    color = AppTheme.colors.background,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(horizontal = AppTheme.sizes.padding)
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_route),
                contentDescription = "route"
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = "filter"
            )
        }
    }
}

