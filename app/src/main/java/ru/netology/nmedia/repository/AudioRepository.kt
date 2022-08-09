package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.viewmodel.AudioViewModel

interface AudioRepository {
    fun playById(id: Long)
    fun pauseById(id: Long)
    fun getAll(): LiveData<List<AudioViewModel>>
}
