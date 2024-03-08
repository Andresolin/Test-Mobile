package org.d3if3002.testmobile.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3002.testmobile.R
import org.d3if3002.testmobile.ui.theme.TestMobileTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {padding ->
        ScreenContent(Modifier.padding(padding))
    }
}


@Composable
fun ScreenContent(modifier: Modifier) {
    var panjang by remember { mutableStateOf("")}
    var panjangEror by remember { mutableStateOf(false)}

    var lebar by remember { mutableStateOf("")}
    var lebarEror by remember { mutableStateOf(false)}

    var hitungLuas by remember { mutableFloatStateOf(0f) }
    var hitungKeliling by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),  // jarak vertikal antar variabel yang di colum
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.hitung_persegi_panjang_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = panjang,
            onValueChange = { panjang = it },
            label = { Text(text = stringResource(id = R.string.panjang_persegi_panjang)) },
            isError = panjangEror,
            trailingIcon = { IconPickker(panjangEror, "cm") },
            supportingText = { ErorHint(panjangEror)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = lebar,
            onValueChange = { lebar = it },
            label = { Text(text = stringResource(id = R.string.lebar_persegi_panjang)) },
            isError = lebarEror,
            trailingIcon = { IconPickker(lebarEror, "cm") },
            supportingText = { ErorHint(lebarEror)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier    = Modifier.fillMaxWidth()
        )

        Row (
            modifier = Modifier
                .padding(top = 16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    panjangEror = (panjang == "" || panjang == "0")
                    lebarEror = (lebar == "" || lebar    == "0")
                    if (panjangEror || lebarEror ) return@Button

                    hitungLuas = hitungLuas(panjang.toFloat(), lebar.toFloat())
                    hitungKeliling = hitungKeliling(panjang.toFloat(), lebar.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }

            Spacer(modifier = Modifier.width(18.dp)) // jarak antar 2 button

            Button(
                onClick = {
                    panjang = ""
                    lebar = ""
                    panjangEror = false
                    lebarEror = false
                    hitungLuas = 0f
                    hitungKeliling = 0f
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                )
            {
                Text(text = stringResource(id = R.string.reset))
            }
        }





        if (hitungLuas != 0f || hitungKeliling !=0f) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.luas_x, hitungLuas),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.keliling_x, hitungKeliling),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun IconPickker(isEror: Boolean, unit: String) {
    if (isEror) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }

}

@Composable
fun ErorHint(isEror: Boolean ) {
    if (isEror) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

private fun hitungLuas(panjang: Float, lebar: Float): Float{
    return panjang * lebar
}

private fun hitungKeliling(panjang: Float, lebar: Float): Float{
    return 2 * (panjang + lebar)
}




@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    TestMobileTheme {
        MainScreen()
    }
}
