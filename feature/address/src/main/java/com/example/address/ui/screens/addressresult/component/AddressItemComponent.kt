package com.example.address.ui.screens.addressresult.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.address.R
import com.example.address.domain.model.AddressItemData

@Composable
fun AddressItemComponent(data: AddressItemData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ItemInfoComponent(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.first_name_title),
                value = data.firstName,
                maxLines = 1
            )
            ItemInfoComponent(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.last_name_title),
                value = data.lastName,
                maxLines = 1,
            )
        }
        ItemInfoComponent(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.address_title),
            value = data.address,
            maxLines = 2,
        )
        ItemInfoComponent(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.mobile_title),
            value = data.mobile,
        )
    }
}


@Composable
fun ItemInfoComponent(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    maxLines: Int = Int.MAX_VALUE,
) {
    Row(
        modifier = modifier.padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = MaterialTheme.colorScheme.surface)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            color = MaterialTheme.colorScheme.surface,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@PreviewFontScale
@PreviewScreenSizes
@PreviewLightDark
@Composable
fun AddressItemComponentPreview() {
    AddressItemComponent(
        data = AddressItemData(
            firstName = "Ali",
            lastName = "Rezaei",
            address = "Tehran, Iran",
            mobile = "09123456789"
        )
    )
}