package dev.thfrod.charliecandys.models

import androidx.compose.ui.graphics.vector.ImageVector

data class Navegacao (
    val rota: String,
    val titulo: String,
    val icone: ImageVector,
    var selecionado: Boolean
)