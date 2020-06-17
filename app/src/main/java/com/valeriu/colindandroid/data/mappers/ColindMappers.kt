package com.valeriu.colindandroid.data.mappers

import com.valeriu.colindandroid.data.models.ColindDto
import com.valeriu.colindandroid.data.models.ColindEntity

fun ColindEntity.toDto() = ColindDto(
    id = this.id,
    title = this.title,
    text = this.text,
    author = this.author
)

fun ColindDto.toEntity() = ColindEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    author = this.author
)